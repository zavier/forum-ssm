package com.forum.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.forum.entity.Board;
import com.forum.entity.User;
import com.forum.service.ForumService;
import com.forum.user.service.UserService;
import com.forum.util.ResultSet;

/*
 * 论坛管理的控制器 包括添加论坛版块、指定论坛版块管理员、对用户进行锁定/解锁
 */
@Controller
public class ForumManagerController {

    private static final Logger log = LoggerFactory.getLogger(ForumManagerController.class);

    @Autowired
    private ForumService forumService;

    @Autowired
    private UserService userService;

    // 进入首页
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/listAllBoards";
    }

    // 列出所有的论坛模块
    @RequestMapping(value = "/searchAllBoards", method = RequestMethod.GET,
            produces = "application/json")
    public @ResponseBody List<Board> listAllBoards(HttpServletRequest request) {
        return forumService.getAllBoards();
    }

    // 添加一个版块的页面
    @RequestMapping(value = "/board/addBoardPage", method = RequestMethod.GET)
    public String addBoardPage() {
        return "/addBoardPage";
    }

    // 添加或修改一个版块
    @RequestMapping(value = "/board/addOrUpdateBoard", method = RequestMethod.POST,
            produces = "application/json")
    public @ResponseBody ResultSet addBoard(Board board) {
        log.info("addOrUpdateBoard is : " + board);
        boolean res;
        ResultSet result = new ResultSet();

        res = forumService.ifExistBoardName(board);
        if (res) { // 不论添加或修改，首先判断是否存在同名版块
            result.setStateCode(1);
            log.info("存在同名板块");
            result.setMessage("已存在同名板块");
            return result;
        }

        result.setStateCode(0);
        if (board.getId() == null) { // 进行新增板块
            forumService.addBoard(board);
            log.info("版块添加成功");
            result.setMessage("板块添加成功");
        } else { // 进行修改板块
            forumService.updateBoard(board);
            log.info("板块修改成功");
            result.setMessage("板块修改成功");
        }

        return result;
    }

    // 删除版块
    @RequestMapping(value = "/forum/deleteBoard", method = RequestMethod.GET,
            produces = "application/json")
    public @ResponseBody ResultSet deleteBoard(String boardId) {
        log.info("deleteBoard id is : " + boardId);
        ResultSet result = new ResultSet();
        forumService.deleteBoard(boardId);
        log.info("板块删除成功");
        result.setStateCode(0);
        result.setMessage("删除板块成功");
        return result;
    }

    // 管理板块页面
    @RequestMapping(value = "/forum/updateBoardPage/{boardId}", method = RequestMethod.GET)
    public ModelAndView updateBoardPage(@PathVariable String boardId) {
        // TODO
        ModelAndView view = new ModelAndView();

        Board board = forumService.getBoardById(boardId);
        view.addObject("board", board);
        view.setViewName("/updateBoardPage");
        return view;
    }

    // 指定论坛管理员的页面
    public ModelAndView setBoardManagerPage() {
        ModelAndView view = new ModelAndView();
        List<Board> boards = forumService.getAllBoards();
        List<User> users = userService.getAllUsers();
        view.addObject("boards", boards);
        view.addObject("users", users);
        view.setViewName("/setBoardManager");
        return view;
    }
}

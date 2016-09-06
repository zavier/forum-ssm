package com.forum.board.web;

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

import com.forum.board.service.BoardService;
import com.forum.entity.Board;
import com.forum.util.entity.ResultSet;

/**
 * 论坛板块的控制器
 */
@Controller
@RequestMapping(value = "/board")
public class BoardController {

    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService boardService;

    /**
     * 列出所有的论坛板块
     */
    @RequestMapping(value = "/searchAllBoards", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public List<Board> listAllBoards(HttpServletRequest request) {
        return boardService.getAllBoards();
    }

    /**
     * 添加一个版块的页面
     */
    @RequestMapping(value = "/addBoardPage", method = RequestMethod.GET)
    public String addBoardPage() {
        return "/board/addBoardPage";
    }

    /**
     * 添加或修改一个版块
     */
    @RequestMapping(value = "/addOrUpdateBoard", method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public ResultSet addBoard(Board board) {
        log.info("addOrUpdateBoard is : " + board);
        boolean res;
        ResultSet result = new ResultSet();

        res = boardService.ifExistBoardName(board);
        if (res) { // 不论添加或修改，首先判断是否存在同名版块
            result.setStateCode(1);
            log.info("存在同名板块");
            result.setMessage("已存在同名板块");
            return result;
        }

        result.setStateCode(0);
        if (board.getId() == null) { // 进行新增板块
            boardService.addBoard(board);
            log.info("版块添加成功");
            result.setMessage("板块添加成功");
        } else { // 进行修改板块
            boardService.updateBoard(board);
            log.info("板块修改成功");
            result.setMessage("板块修改成功");
        }

        return result;
    }

    /**
     * 删除版块
     */
    @RequestMapping(value = "/deleteBoard", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public ResultSet deleteBoard(String boardId) {
        log.info("删除板块的 id  : " + boardId);
        ResultSet result = new ResultSet();
        boardService.deleteBoard(boardId);
        log.info("板块删除成功");
        result.setStateCode(0);
        result.setMessage("删除板块成功");
        return result;
    }

    /**
     * 管理板块页面
     * 
     * @param boardId
     * @return
     */
    @RequestMapping(value = "/updateBoardPage/{boardId}", method = RequestMethod.GET)
    public ModelAndView updateBoardPage(@PathVariable String boardId) {
        ModelAndView view = new ModelAndView();
        Board board = boardService.getBoardById(boardId);
        view.addObject("board", board);
        view.setViewName("/board/updateBoardPage");
        return view;
    }

}

package com.forum.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.forum.entity.User;
import com.forum.user.service.UserService;

/**
 * 用户注册的控制器
 * 
 * @author zhangbin-neu
 *
 */
@Controller
@RequestMapping(value = "/user")
public class RegisterController {

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    /**
     * 用户注册的页面
     * 
     * @return
     */
    @RequestMapping(value = "/registerPage")
    public String registerPage() {
        return "/user/registerPage";
    }

    /**
     * 用户注册处理
     * 
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request,@Valid User user) {
        ModelAndView view = new ModelAndView();
        switch (userService.register(user)) {
            case CORRECT_USER:
                log.info("注册" + user.getUserName() + "成功");
                view.setViewName("redirect:/");
                break;
            case USERNAME_EXISTED:
                log.info("注册失败，用户名已存在");
                view.setViewName("/user/registerPage");
                view.addObject("errorMsg", "用户名已存在！");
                break;
            default:
                break;
        }
        return view;
    }
}

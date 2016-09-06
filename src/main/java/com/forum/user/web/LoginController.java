package com.forum.user.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.forum.user.service.UserService;
import com.forum.util.MD5Util;

/**
 * 用户登录、注销的控制器
 * 
 * @author zhangbin-neu
 *
 */
@Controller
@RequestMapping(value = "/user")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    /**
     * 用户登录页面
     * 
     * @return
     */
    @RequestMapping(value = "/login/loginPage")
    public String registerPage() {
        return "user/loginPage";
    }

    /**
     * 用户登录处理
     */
    @RequestMapping(value = "/login/doLogin")
    public ModelAndView login(HttpServletRequest request, String userName, String password) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/loginPage"); // 设置登录失败是返回的界面

        String md5Password = MD5Util.md5(password);
        UsernamePasswordToken token = new UsernamePasswordToken(userName, md5Password);
        Subject subject = SecurityUtils.getSubject();
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            log.info("对用户[" + userName + "]进行登录验证..验证开始");
            subject.login(token);
            log.info("对用户[" + userName + "]进行登录验证..验证通过");

            userService.loginSuccess(userName, request); // 对登录成功进行相关日志记录
            mav.setViewName("redirect:/"); // 设置登录成功返回的界面

        } catch (UnknownAccountException uae) {
            log.info("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
            mav.addObject("errorMsg", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            log.info("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");
            mav.addObject("errorMsg", "密码不正确");
        } catch (LockedAccountException lae) {
            log.info("对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定");
            mav.addObject("errorMsg", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            log.info("对用户[" + userName + "]进行登录验证..验证未通过,错误次数过多");
            mav.addObject("errorMsg", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            // 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.info("对用户[" + userName + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            mav.addObject("errorMsg", "用户名或密码不正确");
        }
        // 验证是否登录成功
        if (subject.isAuthenticated()) {
            log.info("用户[" + userName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
        } else {
            token.clear();
        }

        return mav;
    }

    /**
     * 用户注销
     */
    @RequestMapping("/login/doLogout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("LOGINUSER");
        SecurityUtils.getSubject().logout();
        return "redirect:/";
    }
}

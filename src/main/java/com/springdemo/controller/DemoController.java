package com.springdemo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.forum.dao.UserMapper;

@Controller
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String index(String username, String password, HttpServletRequest request) {
        log.info("username:" + username + "--password:" + password);

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // token.setRememberMe(true);
        System.out.println("为了验证登录用户而封装的token为"
                + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        // 获取当前的Subject
        Subject subject = SecurityUtils.getSubject();
        boolean checkUser = false;
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            System.out.println("对用户[" + username + "]进行登录验证..验证开始");
            subject.login(token);
            System.out.println("对用户[" + username + "]进行登录验证..验证通过");
            checkUser = true;
        } catch (UnknownAccountException uae) {
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            request.setAttribute("message_login", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            request.setAttribute("message_login", "密码不正确");
        } catch (LockedAccountException lae) {
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            request.setAttribute("message_login", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            request.setAttribute("message_login", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            // 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            request.setAttribute("message_login", "用户名或密码不正确");
        }
        // 验证是否登录成功
        if (subject.isAuthenticated()) {
            System.out.println("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
        } else {
            token.clear();
        }

        if (checkUser) {
            return "index";
        } else {
            return "redirect:/";
        }
    }


    /**
     * 用户登出
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
    }
    /*
     * @RequestMapping("/test") public String test() { User user = new User();
     * 
     * // 查找所有数据 System.out.println("初始数据：" + userMapper.getAllUser()); // 增加
     * user.setUsername("lisi"); user.setPassword("123");; userMapper.addUser(user);
     * System.out.println("增加后：" + userMapper.getAllUser()); // 修改 user.setId(1);
     * user.setUsername("wangwu"); user.setPassword("wang"); userMapper.updateUser(user);
     * System.out.println("修改后：" + userMapper.getAllUser()); // 删除 userMapper.deleteUserById(5);
     * System.out.println("删除后：" + userMapper.getAllUser()); // 查找 System.out.println("查找id为1的值: " +
     * userMapper.getUserById(1));
     * 
     * return "test"; }
     * 
     * @RequestMapping(value = "/add", method = RequestMethod.POST)
     * 
     * @ResponseBody public Map<String, Object> add(String name) { log.info("insert name : " +
     * name); User user = new User(); user.setUsername(name); userMapper.addUser(user); Map<String,
     * Object> map = new HashMap<>(); map.put("success", true); return map; }
     * 
     * @RequestMapping(value = "/delete", method = RequestMethod.GET)
     * 
     * @ResponseBody public Map<String, Object> delete(String name) { log.info("delete name : " +
     * name); userMapper.deleteUserByName(name); Map<String, Object> map = new HashMap<>();
     * map.put("success", true); return map; }
     * 
     * @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
     * 
     * @ResponseBody public List<User> getAllUser() { List<User> userList = userMapper.getAllUser();
     * log.info("获取所有用户信息 : " + userList); return userList; }
     */
}

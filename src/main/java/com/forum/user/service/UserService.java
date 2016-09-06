package com.forum.user.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.dao.LoginLogMapper;
import com.forum.dao.UserMapper;
import com.forum.entity.LoginLog;
import com.forum.entity.User;
import com.forum.util.CommonUtil;

// 用户管理服务器，负责查询用户、注册用户、锁定用户等操作
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public static final int USER_LOCKED = 1;
    public static final int USER_UNLOCK = 0;

    public enum UserStatus {
        ERROR_PASSWORD, NOT_USERNAME, USER_LOCKED, CORRECT_USER, USERNAME_EXISTED
    };

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginLogMapper loginLogMapper;

    /*
     * 注册新用户
     */
    @Transactional
    public UserStatus register(User user) {
        String userName = user.getUserName();
        log.info("要注册的用户名为:" + userName);
        User findUser = userMapper.selectByUserName(userName);
        log.info("数据库中查询要注册的用户名结果为:" + findUser);
        if (findUser != null) {
            return UserStatus.USERNAME_EXISTED;
        } else {
            String primaryKey = CommonUtil.getUUIDPrimaryKey();
            user.setId(primaryKey);
            user.setUserType(1);
            user.setCredit(5);
            user.setPassword(CommonUtil.md5(user.getPassword()));
            userMapper.insert(user);
            return UserStatus.CORRECT_USER;
        }
    }

    /*
     * 通过用户名查询用户
     */
    @Transactional(readOnly = true)
    public User getUserByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    /*
     * 锁定用户
     */
    @Transactional
    public void lockUser(String userName) {
        User user = userMapper.selectByUserName(userName);
        user.setLocked(USER_LOCKED);
        userMapper.insert(user);
    }

    /*
     * 解除锁定的用户
     */
    @Transactional
    public void unlockUser(String userName) {
        User user = userMapper.selectByUserName(userName);
        user.setLocked(USER_UNLOCK);
        userMapper.insert(user);
    }

    /*
     * 登录成功并记录日志,添加session
     */
    @Transactional
    public void loginSuccess(String username, HttpServletRequest request) {
        LoginLog loginLog = new LoginLog();
        loginLog.setId(CommonUtil.getUUIDPrimaryKey());
        loginLog.setIp(request.getRemoteAddr());
        loginLog.setLoginDatetime(new Date());
        User findUser = userMapper.selectByUserName(username);
        loginLog.setUserId(findUser.getId()); // 设置loginLog中的User外键
        loginLogMapper.insert(loginLog);
        request.getSession().setAttribute("LOGINUSER", findUser);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }


    // 判断要登录的用户是否存在以及状态
    @Transactional(readOnly = true)
    public UserStatus ifExistUser(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        User findUser = new User();
        if ((findUser = userMapper.selectByUserName(userName)) == null) {
            return UserStatus.NOT_USERNAME;
        } else if (!password.equals(findUser.getPassword())) {
            return UserStatus.ERROR_PASSWORD;
        } else if (findUser.getLocked() == UserService.USER_LOCKED) {
            return UserStatus.USER_LOCKED;
        }
        return UserStatus.CORRECT_USER;
    }

    @Transactional(readOnly = true)
    public User findById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Transactional
    public User save(User user) {
        userMapper.insert(user);
        return null;
    }

    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }
}

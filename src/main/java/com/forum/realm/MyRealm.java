package com.forum.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.forum.dao.UserMapper;
import com.forum.entity.User;

/**
 * 自定义的指定Shiro验证用户登录的类
 */
public class MyRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 为当前登录的Subject授予角色和权限
     * 
     * @see 经测试:本例中该方法的调用时机为需授权资源被访问时
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
        String currentUsername = (String) super.getAvailablePrincipal(principals);

        List<String> roleList = new ArrayList<String>();
        List<String> permissionList = new ArrayList<String>();
        // 从数据库中获取当前登录用户的详细信息
        User user = userMapper.selectByUserName(currentUsername);
        // if(null != user){
        // 实体类User中包含有用户角色的实体类信息
        // if(null!=user.getRoles() && user.getRoles().size()>0){
        // //获取当前登录用户的角色
        // for(Role role : user.getRoles()){
        // roleList.add(role.getName());
        // //实体类Role中包含有角色权限的实体类信息
        // if(null!=role.getPermissions() && role.getPermissions().size()>0){
        // //获取权限
        // for(Permission pmss : role.getPermissions()){
        // if(!StringUtils.isEmpty(pmss.getPermission())){
        // permissionList.add(pmss.getPermission());
        // }
        // }
        // }
        // }
        // }
        // }else{
        // throw new AuthorizationException();
        // }
        // //为当前用户设置角色和权限
        // SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        // simpleAuthorInfo.addRoles(roleList);
        // simpleAuthorInfo.addStringPermissions(permissionList);
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        simpleAuthorInfo.addRole("superadmin");
        simpleAuthorInfo.addStringPermission("superadmin:manage");
        log.info("已为用户[" + currentUsername + "]赋予了[admin]角色和[admin:manage]权限");
        return simpleAuthorInfo;
        // 实际中可能会像上面注释的那样从数据库取得
        // if (null != currentUsername && "jadyer".equals(currentUsername)) {
        // 添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
        // simpleAuthorInfo.addRole("admin");
        // 添加权限
        // simpleAuthorInfo.addStringPermission("admin:manage");
        // System.out.println("已为用户[jadyer]赋予了[admin]角色和[admin:manage]权限");
        // return simpleAuthorInfo;
        // } else if (null != currentUsername && "玄玉".equals(currentUsername)) {
        // System.out.println("当前用户[玄玉]无授权");
        // return simpleAuthorInfo;
        // }
        // 若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址
        // 详见applicationContext.xml中的<bean id="shiroFilter">的配置
    }


    /**
     * 验证当前登录的Subject
     * 
     * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        // 获取基于用户名和密码的令牌
        // 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        // 两个token的引用都是一样的,本例中是org.apache.shiro.authc.UsernamePasswordToken@33799a1e
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        System.out.println("验证当前Subject时获取到token为"
                + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        User user = userMapper.selectByUserName(token.getUsername());
        if (null != user) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(),
                    user.getPassword(), user.getUserName());
            // this.setSession("currentUser", user);
            return authcInfo;
        } else {
            return null;
        }
    }


    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     */
    public static void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }
}

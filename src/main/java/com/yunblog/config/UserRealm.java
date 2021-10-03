package com.yunblog.config;

import com.yunblog.entiy.User;
import com.yunblog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @Description: 自定义Realm 需要extends AuthorizingRealm
 */
public class UserRealm extends AuthorizingRealm {


    @Autowired
    UserService userService;

    @Autowired
    com.yunblog.config.CredentialsMatcher credentialsMatcher;

    /**
     * @Description: shiro授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //SimpleAuthorizationInfo
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //authorizationInfo.addStringPermission("user:add");

        //获取当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        User current = (User) subject.getPrincipal();

        authorizationInfo.addStringPermission(String.valueOf(current));

        return authorizationInfo;
    }


    /**
     * @Description: shiro认证
     * @param authenticationToken
     * @return 密码认证
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.Usercheck(token.getUsername());

        if (user == null){
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(),"");
    }

    /**
     * 重写shiro的密码验证，让shiro用我自己的验证
     */
    @PostConstruct
    public void myCredentialsMatcher() {

        setCredentialsMatcher(credentialsMatcher);
    }
}

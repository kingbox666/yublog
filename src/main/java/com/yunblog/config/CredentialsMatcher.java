package com.yunblog.config;

import com.yunblog.util.MD5Utils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.stereotype.Component;

/**
 * @Description: 自定义的 CredentialsMatcher方法  用于shiro的密码验证
 */
@Component
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    /**
     *
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
        Object tokenCredentials = MD5Utils.code(String.valueOf(passwordToken.getPassword()));
        Object accountCredentials = getCredentials(info);
        return accountCredentials.equals(tokenCredentials);
    }

}

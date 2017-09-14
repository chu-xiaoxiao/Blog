package com.zyc.realm;

import com.zyc.mapper.UserMapper;
import com.zyc.model.User;
import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YuChen Zhang on 17/09/14.
 */

public class BOSrealm extends AuthorizingRealm{
    @Autowired
    UserMapper userMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //从令牌中获取用户名
        String username = token.getUsername();
        User user = userMapper.findByName(username);
        if(user==null){
            return null;
        }else{
            String password = user.getPssword();
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,password,this.getClass().getSimpleName());
            return info;
        }
    }
}

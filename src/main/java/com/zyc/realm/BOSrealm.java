package com.zyc.realm;

import com.zyc.mapper.UserMapper;
import com.zyc.model.User;
import com.zyc.model.UserExample;
import com.zyc.service.UserService;
import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by YuChen Zhang on 17/09/14.
 */
@Component("bOSrealm")
public class BOSrealm extends AuthorizingRealm{
    @Autowired
    UserMapper userMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //从令牌中获取用户名
        String username = token.getUsername();
        UserExample userExample = new UserExample();
        userExample.getOredCriteria().add(userExample.createCriteria().andUsernameEqualTo(username));
        User user = userMapper.selectByExample(userExample).get(0);
        if(user==null){
            return null;
        }else{
            String password = user.getUserpassword();
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,password,this.getClass().getSimpleName());
            return info;
        }
    }
}

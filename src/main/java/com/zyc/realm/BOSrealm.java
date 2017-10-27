package com.zyc.realm;

import com.zyc.mapper.UserMapper;
import com.zyc.model.Power;
import com.zyc.model.Role;
import com.zyc.model.User;
import com.zyc.model.UserExample;
import com.zyc.service.PowerService;
import com.zyc.service.RoleService;
import com.zyc.util.MyException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuChen Zhang on 17/09/14.
 */
@Component("bOSrealm")
public class BOSrealm extends AuthorizingRealm{

    @Autowired
    UserMapper userMapper;

    @Autowired
    @Qualifier("powerServiceImplements")
    PowerService powerService;

    @Autowired
    @Qualifier("roleServiceImplements")
    RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        Session session = SecurityUtils.getSubject().getSession();
        //用户赋予角色操作
        List<Role> roles = (List<Role>) session.getAttribute("roles");
        try {
            //判断当前用户角色是否授权成功，如果是，则不进行数据库查询否则查询数据库授权
            if(roles==null) {
                roles = roleService.getRolesByUserName(user.getUsername());
                session.setAttribute("roles",roles);
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
        //根据用户角色授权操作
        List<Power> powers = (List<Power>) session.getAttribute("powers");
        //判断当前用户权限是否授权成功，如果是，则不进行数据库查询否则查询数据库授权
        if(powers==null) {
            powers = powerService.getPowerByRoles(roles);
            session.setAttribute("powers",powers);
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for(Role temp : roles) {
            simpleAuthorizationInfo.addRole(temp.getRolename());
        }

        for(Power temp : powers){
            simpleAuthorizationInfo.addStringPermission(temp.getPowername());
        }

        return simpleAuthorizationInfo;
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
            throw new AuthenticationException();
        }else{
            String password = user.getUserpassword();
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,password,this.getClass().getSimpleName());
            return info;
        }
    }
}
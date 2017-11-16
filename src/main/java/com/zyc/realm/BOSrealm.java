package com.zyc.realm;

import com.zyc.mapper.UserMapper;
import com.zyc.model.Power;
import com.zyc.model.Role;
import com.zyc.model.User;
import com.zyc.model.UserExample;
import com.zyc.service.PowerService;
import com.zyc.service.RoleService;
import com.zyc.service.UserService;
import com.zyc.util.MyException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by YuChen Zhang on 17/09/14.
 */
/*@Component("bosRealm")*/
public class BOSrealm extends CasRealm implements Serializable {

    @Autowired
    UserMapper userMapper;

    @Autowired
    @Qualifier("userServiceImplement")
    UserService userService;


    @Autowired
    @Qualifier("powerServiceImplements")
    PowerService powerService;

    @Autowired
    @Qualifier("roleServiceImplements")
    RoleService roleService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = (String) principalCollection.getPrimaryPrincipal();
        User user =userService.findByPrimaryKey(Integer.parseInt(username));
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("user",user);
        //用户赋予角色操作
        List<Role> roles = null;
        try {
            roles = roleService.getRolesByUserName(user.getUsername());
            if(roles==null||roles.size()==0){
                Role role = new Role();
                role.setRoleid(3);
                roleService.authorization(user,role);
                roles = new ArrayList<Role>();
                roles.add(role);
            }
        }  catch (MyException e) {
            e.printStackTrace();
        }
        //根据用户角色授权操作
        List<Power> powers = null;
        powers = powerService.getPowerByRoles(roles);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role temp : roles) {
            simpleAuthorizationInfo.addRole(temp.getRolename());
        }

        for (Power temp : powers) {
            simpleAuthorizationInfo.addStringPermission(temp.getPowername());
        }

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       return super.doGetAuthenticationInfo(authenticationToken);
    }
}
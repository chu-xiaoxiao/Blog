package com.zyc.realm;

import com.zyc.mapper.UserMapper;
import com.zyc.model.Power;
import com.zyc.model.Role;
import com.zyc.model.User;
import com.zyc.service.PowerService;
import com.zyc.service.RoleService;
import com.zyc.service.UserService;
import com.zyc.util.MyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by YuChen Zhang on 17/09/14.
 */
/*@Component("bosRealm")*/
public class BOSrealm extends CasRealm implements Serializable {
    private static final Logger log = LogManager.getLogger(BOSrealm.class);

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

        String sessionIdAndUserid =(String) principalCollection.getPrimaryPrincipal();
        String userid = sessionIdAndUserid.split("__")[1];
        User user = userService.findByPrimaryKey(Integer.parseInt(userid));
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
        CasToken casToken = (CasToken)authenticationToken;
        if(authenticationToken == null) {
            return null;
        } else {
            String ticket = (String)casToken.getCredentials();
            if(!StringUtils.hasText(ticket)) {
                return null;
            } else {
                TicketValidator ticketValidator = this.ensureTicketValidator();

                try {
                    Assertion casAssertion = ticketValidator.validate(ticket, this.getCasService());
                    AttributePrincipal casPrincipal = casAssertion.getPrincipal();
                    String userId = casPrincipal.getName();
                    log.debug("Validate ticket : {} in CAS server : {} to retrieve user : {}", new Object[]{ticket, this.getCasServerUrlPrefix(), userId});
                    Map<String, Object> attributes = casPrincipal.getAttributes();
                    /*attributes.put(casPrincipal.getName(),userService.findByPrimaryKey(Integer.parseInt(userId)));*/
                    casToken.setUserId(SecurityUtils.getSubject().getSession().getId()+"__"+userId);
                    String rememberMeAttributeName = this.getRememberMeAttributeName();
                    String rememberMeStringValue = (String)attributes.get(rememberMeAttributeName);
                    boolean isRemembered = rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue);
                    if(isRemembered) {
                        casToken.setRememberMe(true);
                    }
                    List<Object> principals = CollectionUtils.asList(new Object[]{SecurityUtils.getSubject().getSession().getId()+"__"+userId, attributes});
                    PrincipalCollection principalCollection = new SimplePrincipalCollection(principals, this.getName());
                    return new SimpleAuthenticationInfo(principalCollection, ticket);
                } catch (TicketValidationException var14) {
                    throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", var14);
                }
            }
        }
    }
}
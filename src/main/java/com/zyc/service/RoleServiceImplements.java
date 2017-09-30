package com.zyc.service;

import com.zyc.mapper.RoleMapper;
import com.zyc.mapper.UserMapper;
import com.zyc.mapper.UsertoroleMapper;
import com.zyc.model.*;
import com.zyc.util.MyException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuChen Zhang on 17/09/29.
 */
@Service("roleServiceImplements")
public class RoleServiceImplements implements  RoleService {
    Logger logger = LogManager.getLogger(RoleService.class);
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UsertoroleMapper usertoroleMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<Role> getRolesByUserName(String username) throws MyException {
        //获取用户
        User user = new User();
        UserExample userExample = new UserExample();
        userExample.getOredCriteria().add(userExample.createCriteria().andUsernameEqualTo(username));
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()>0) {
            user = users.get(0);
        }else{
            throw  new MyException("用户名不存在");
        }
        //查询当前用户所拥有的角色
        UsertoroleExample usertoroleExample = new UsertoroleExample();
        usertoroleExample.getOredCriteria().add(usertoroleExample.createCriteria().andUseridEqualTo(user.getId()));
        List<Usertorole> usertoroles = usertoroleMapper.selectByExample(usertoroleExample);
        List<Integer> roleid = new ArrayList<Integer>();
        for(Usertorole temp : usertoroles){
            roleid.add(temp.getRoleid());
        }

        List<Role> roles = new ArrayList<Role>();
        RoleExample roleExample = new RoleExample();
        roleExample.getOredCriteria().add(roleExample.createCriteria().andRoleidIn(roleid));
        roles = roleMapper.selectByExample(roleExample);
        return roles;
    }

    @Override
    @Transactional(readOnly=true,propagation= Propagation.REQUIRED)
    public void InsertUserToRole(Usertorole usertorole) {
        usertoroleMapper.insert(usertorole);
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public int authorization(User user, Role role) {
        Usertorole usertorole = new Usertorole();
        //根据注册用户查询id
        UserExample userExample = new UserExample();
        userExample.getOredCriteria().add(userExample.createCriteria().andUsernameEqualTo(user.getUsername()));
        User user1 = userMapper.selectByExample(userExample).get(0);
        //根据id封装权限
        usertorole.setUserid(user.getId());
        usertorole.setRoleid(role.getRoleid());
        this.InsertUserToRole(usertorole);
        logger.info("用户"+user.getUsername()+"授权成功");
        return 0;
    }


}

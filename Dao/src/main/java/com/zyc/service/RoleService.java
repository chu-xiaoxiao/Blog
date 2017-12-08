package com.zyc.service;

import com.zyc.model.Role;
import com.zyc.model.User;
import com.zyc.model.Usertorole;
import com.zyc.util.MyException;

import java.util.List;

/**
 * Created by YuChen Zhang on 17/09/29.
 */

public interface RoleService {
    List<Role> getRolesByUserName(String username) throws MyException;
    void InsertUserToRole(Usertorole usertorole);

    /**
     * 用户授权
     * @param user
     * @param role
     * @return
     */
    int authorization(User user, Role role);
}

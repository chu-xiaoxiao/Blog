package com.zyc.service;

import com.zyc.model.Power;
import com.zyc.model.Role;

import java.util.List;

/**
 * Created by YuChen Zhang on 17/09/29.
 */
public interface PowerService {
    List<Power> getPowerByRoles(List<Role> roles);
}

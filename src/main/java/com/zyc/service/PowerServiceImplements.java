package com.zyc.service;

import com.zyc.mapper.PowerMapper;
import com.zyc.mapper.RoletopowerMapper;
import com.zyc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuChen Zhang on 17/09/29.
 */
@Service("powerServiceImplements")
public class PowerServiceImplements implements  PowerService{

    @Autowired
    RoletopowerMapper roletopowerMapper;

    @Autowired
    PowerMapper powerMapper;

    @Override
    public List<Power> getPowerByRoles(List<Role> roles) {
        List<Integer> roleid = new ArrayList<Integer>();
        for(Role temp : roles){
            roleid.add(temp.getRoleid());
        }
        //获取当前用户角色的所有权限id
        RoletopowerExample roletopowerExample = new RoletopowerExample();
        roletopowerExample.createCriteria().andRoleidIn(roleid);
        List<Roletopower> roletopowers = roletopowerMapper.selectByExample(roletopowerExample);
        List<Integer> poserid = new ArrayList<Integer>();
        for(Roletopower temp : roletopowers){
            poserid.add(temp.getPowerid());
        }
        //根据id获取所有的权限
        PowerExample powerExample = new PowerExample();
        powerExample.createCriteria().andPoweridIn(poserid);
        List<Power> powers = powerMapper.selectByExample(powerExample);

        return powers;
    }
}

package com.zyc.service;

import javax.annotation.Resource;

import com.zyc.model.UserExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zyc.mapper.UserMapper;
import com.zyc.model.User;

import java.util.List;

@Service("userServiceImplement")
@Transactional
public class UserServiceImplement implements UserService{
	@Resource
	public UserMapper UserMapper;
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)

	public String findUsername(Integer id) {
		User user = UserMapper.selectByPrimaryKey(id);
		return user.getUsername();
	}
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public User findUser(Integer id) {
		User user = UserMapper.selectByPrimaryKey(id);
		return user;
	}
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	public int insertuUser(User user) {
		return UserMapper.insert(user);
	}
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public User logIn(User user) {
		UserExample userExample = new UserExample();
		userExample.getOredCriteria().add(userExample.createCriteria().andUsernameEqualTo(user.getUsername()).andUserpasswordEqualTo(user.getUserpassword()));
		return UserMapper.selectByExample(userExample).get(0);
	}
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public User findByName(String name) {
		UserExample userExample = new UserExample();
		userExample.getOredCriteria().add(userExample.createCriteria().andUsernameEqualTo(name));
		List<User> result =  UserMapper.selectByExample(userExample);
		User user = null;
		if(result.size()>0){
			user = result.get(0);
		}else{
			user = null;
		}
		return user;
	}
}

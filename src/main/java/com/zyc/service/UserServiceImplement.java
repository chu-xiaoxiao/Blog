package com.zyc.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zyc.mapper.UserMapper;
import com.zyc.model.User;
@Service
@Transactional
public class UserServiceImplement implements UserService{
	@Resource
	public UserMapper UserMapper;
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public String findUsername(Integer id) {
		String userName = UserMapper.findUserName(id);
		return userName;
	}
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public User findUser(Integer id) {
		User user = UserMapper.findUser(id);
		return user;
	}
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	public int insertuUser(User user) {
		return UserMapper.insertUser(user);
	}
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public User logIn(User user) {
		return UserMapper.logIn(user);
	}
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public User findByName(String name) {
		User user = UserMapper.findByName(name);
		return user;
	}
}

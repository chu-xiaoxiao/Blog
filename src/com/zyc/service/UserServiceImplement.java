package com.zyc.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zyc.mapper.UserMapper;
import com.zyc.model.User;
@Service
@Transactional
public class UserServiceImplement implements UserService{
	@Resource
	public UserMapper UserMapper;
	@Override
	public String findUsername(Integer id) {
		String userName = UserMapper.findUserName(id);
		return userName;
	}
	@Override
	public User findUser(Integer id) {
		User user = UserMapper.findUser(id);
		return user;
	}
	@Override
	public int insertuUser(User user) {
		return UserMapper.insertUser(user);
	}
	@Override
	public User logIn(User user) {
		return UserMapper.logIn(user);
	}
	@Override
	public User findByName(String name) {
		User user = UserMapper.findByName(name);
		return user;
	}
}

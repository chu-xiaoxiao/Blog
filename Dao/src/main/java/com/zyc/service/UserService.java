package com.zyc.service;

import com.zyc.model.User;
import com.zyc.model.UserExample;

import java.util.List;

public interface UserService {
	String findUsername(Integer id);
	User findUser(Integer id);
	int insertuUser(User user);
	User logIn(User user);
	User findByName(String name);
	void modifyUserInfo(User user);
	User findByPrimaryKey(Integer id);
	List<User> findUserWithPower(UserExample userExample);
	User findUserWithPowerByPrimaryKey(Integer id);
}

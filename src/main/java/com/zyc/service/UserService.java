package com.zyc.service;

import com.zyc.model.Role;
import com.zyc.model.User;

public interface UserService {
	String findUsername(Integer id);
	User findUser(Integer id);
	int insertuUser(User user);
	User logIn(User user);
	User findByName(String name);
	void modifyUserInfo(User user);
}

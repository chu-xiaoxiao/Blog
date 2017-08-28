package com.zyc.mapper;

import com.zyc.model.User;
public interface UserMapper {
	
	User findUser(Integer id);
	String findUserName(Integer id);
	int insertUser(User user);
	User logIn(User user);
	User findByName(String name);
}

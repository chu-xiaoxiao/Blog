package com.zyc.service;

import javax.annotation.Resource;

import com.zyc.model.UserExample;
import com.zyc.aop.CRDU;
import com.zyc.aop.LogAop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zyc.mapper.UserMapper;
import com.zyc.model.User;

import java.util.List;

@Service("userServiceImplement")
@Transactional
public class UserServiceImplement implements UserService{
    private static Logger logger = LogManager.getLogger(UserService.class);

	@Resource
	public UserMapper userMapper;

	@Autowired
	@Qualifier("roleServiceImplements")
	private RoleService roleService;
    @Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public String findUsername(Integer id) {
		User user = userMapper.selectByPrimaryKey(id);
		return user.getUsername();
	}
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public User findUser(Integer id) {
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@LogAop(tableName = "user",CRDU = CRDU.Insert,logRecord = "注册用户")
	public int insertuUser(User user) {

		//注册用户
        int result = userMapper.insert(user);
        logger.info("用户"+user.getUsername()+"注册成功");
		return result;
	}
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public User logIn(User user) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUsernameEqualTo(user.getUsername()).andUserpasswordEqualTo(user.getUserpassword());
		return userMapper.selectByExample(userExample).get(0);
	}
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
    public User findByName(String name) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUsernameEqualTo(name);
		List<User> result =  userMapper.selectByExample(userExample);
		User user = null;
		if(result.size()>0){
			user = result.get(0);
		}else{
			user = null;
		}
		return user;
	}

	@Override
	@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
	@LogAop(tableName = "user",CRDU = CRDU.Update,logRecord = "修改用户信息")
	public void modifyUserInfo(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public User findByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
}

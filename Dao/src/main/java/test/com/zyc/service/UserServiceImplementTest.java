package test.com.zyc.service; 

import com.zyc.mapper.UserMapper;
import com.zyc.model.User;
import com.zyc.model.UserExample;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/** 
* UserServiceImplement Tester. 
* 
* @author <Authors name> 
* @since <pre>十二月 7, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-mybatis.xml","classpath*:spring-redis.xml"})
public class UserServiceImplementTest { 
    @Autowired
    UserMapper userMapper;
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: findUsername(Integer id) 
* 
*/ 
@Test
public void testFindUsername() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findUser(Integer id) 
* 
*/ 
@Test
public void testFindUser() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: insertuUser(User user) 
* 
*/ 
@Test
public void testInsertuUser() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: logIn(User user) 
* 
*/ 
@Test
public void testLogIn() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findByName(String name) 
* 
*/ 
@Test
public void testFindByName() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: modifyUserInfo(User user) 
* 
*/ 
@Test
public void testModifyUserInfo() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findByPrimaryKey(Integer id) 
* 
*/ 
@Test
public void testFindByPrimaryKey() throws Exception {
    System.out.println(userMapper.selectUserWithPowerByPrimaryKey(21).getPowers().size());
}


} 

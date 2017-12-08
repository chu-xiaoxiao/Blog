package test.com.zyc.service; 

import com.zyc.mapper.WenzhangMapper;
import com.zyc.model.WenzhangExample;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.misc.Cache;

/** 
* WenzhangServiceImplements Tester. 
* 
* @author <Authors name> 
* @since <pre>十二月 7, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-mybatis.xml","classpath*:spring-redis.xml","classpath*:spring-simplebean.xml"})
public class WenzhangServiceImplementsTest { 
@Autowired
    WenzhangMapper wenzhangMapper;
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addWenzhang(Wenzhang wenzhang, User user) 
* 
*/ 
@Test
public void testAddWenzhang() throws Exception {
    System.out.println(wenzhangMapper.selectByExample(new WenzhangExample()));
}

/** 
* 
* Method: modifyWenzhang(Wenzhang Wenzhang, User user) 
* 
*/ 
@Test
public void testModifyWenzhang() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findAllWenzhang() 
* 
*/ 
@Test
public void testFindAllWenzhang() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteWenzhang(Integer id, User user) 
* 
*/ 
@Test
public void testDeleteWenzhang() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findWenzhangByid(Integer id, User user) 
* 
*/ 
@Test
public void testFindWenzhangByid() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findWenzhangBySearch(Page2<Wenzhang,WenzhangExample> page2, User user) 
* 
*/ 
@Test
public void testFindWenzhangBySearch() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: countWenzhang(User user) 
* 
*/ 
@Test
public void testCountWenzhang() throws Exception { 
//TODO: Test goes here... 
} 


} 

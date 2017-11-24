package test.com.zyc.util;

import com.zyc.jedis.JedisDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * JedisDataSource Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十一月 21, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"classpath*:spring-redis.xml"})
/*@ContextConfiguration({"classpath*:applicationContext.xml"})*/
@Component
public class JedisDataSourceTest {
    @Autowired
    JedisDataSource jedisDataSource;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getJedis()
     */
    @Test
    public void testGetJedis() throws Exception {
//TODO: Test goes here...
        jedisDataSource.getJedis();
    }

    /**
     * Method: returnRes(Jedis jedis)
     */
    @Test
    public void testReturnRes() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getJedisPassword()
     */
    @Test
    public void testGetJedisPassword() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setJedisPassword(String jedisPassword)
     */
    @Test
    public void testSetJedisPassword() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getJedishost()
     */
    @Test
    public void testGetJedishost() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setJedishost(String jedishost)
     */
    @Test
    public void testSetJedishost() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getPort()
     */
    @Test
    public void testGetPort() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setPort(Integer port)
     */
    @Test
    public void testSetPort() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getMaxIdle()
     */
    @Test
    public void testGetMaxIdle() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setMaxIdle(Integer maxIdle)
     */
    @Test
    public void testSetMaxIdle() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getMaxWaitMillis()
     */
    @Test
    public void testGetMaxWaitMillis() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setMaxWaitMillis(Integer maxWaitMillis)
     */
    @Test
    public void testSetMaxWaitMillis() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getSetMaxTotal()
     */
    @Test
    public void testGetSetMaxTotal() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setSetMaxTotal(Integer setMaxTotal)
     */
    @Test
    public void testSetSetMaxTotal() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getMnEvictableIdleTimeMillis()
     */
    @Test
    public void testGetMnEvictableIdleTimeMillis() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setMnEvictableIdleTimeMillis(Integer mnEvictableIdleTimeMillis)
     */
    @Test
    public void testSetMnEvictableIdleTimeMillis() throws Exception {
//TODO: Test goes here... 
    }


    /**
     * Method: createJedisPool()
     */
    @Test
    public void testCreateJedisPool() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = JedisDataSource.getClass().getMethod("createJedisPool"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: poolInit()
     */
    @Test
    public void testPoolInit() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = JedisDataSource.getClass().getMethod("poolInit"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 

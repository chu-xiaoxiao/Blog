package test.com.zyc.util;

import com.zyc.jedis.JedisPoolUtil1;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

/**
 * JedisPoolUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十一月 21, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"classpath*:applicationContext.xml"})

public class JedisPoolUtilTest {
    @Autowired
    JedisPoolUtil1 jedisPoolUtil;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getValue(String key)
     */
    @Test
    public void testGetValue() throws Exception {
        System.out.println(jedisPoolUtil.get("guowai"));
    }
    @Test
    public void testKey(){
        jedisPoolUtil.set("12345","12345",2,10000);
        jedisPoolUtil.getJedis().select(2);
        System.out.println(jedisPoolUtil.getJedis().ttl("12345"));
    }
    @Test
    public void testk(){
        Set<byte[]> keys = jedisPoolUtil.allKeys("*".getBytes(),1);
        for(byte[]temp:keys){
            System.out.println(jedisPoolUtil.getTtl(temp,1));
        }
    }
    @Test
    public void del(){
        Set<byte[]> keys = jedisPoolUtil.allKeys("*".getBytes(), 1);
        for (byte[] temp : keys) {
            jedisPoolUtil.del(temp, 1);
            System.out.println("删除");
        }
    }
}

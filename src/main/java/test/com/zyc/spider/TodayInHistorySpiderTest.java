package test.com.zyc.spider; 

import com.zyc.spider.TodayInHistorySpider;
import com.zyc.util.JedisPool;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import redis.clients.jedis.Jedis;

import java.util.*;

/** 
* TodayInHistorySpider Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 13, 2017</pre> 
* @version 1.0 
*/ 
public class TodayInHistorySpiderTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception {

} 

/** 
* 
* Method: getHistoryFromURL() 
* 
*/ 
@Test
    public void testGetHistoryFromURL() throws Exception {
        TodayInHistorySpider todayInHistorySpider = new TodayInHistorySpider();
        Map<String,String> result = todayInHistorySpider.getTodayInHistory();
        for(Map.Entry<String,String> temp:result.entrySet()){
            System.out.println(temp.getKey()+"\t"+temp.getValue());
        }
    }
    @Test
    public void testRand(){
        List<String> temp = new ArrayList<>();
        String[] temp2 = {"123","234"};
        temp.addAll(Arrays.asList(temp2));
        temp.add("2345");
        System.out.println(temp.size());
    }
    @Test
    public void testREdis(){
        Jedis jedis = JedisPool.getJedis();
        jedis.set("zyc","1016");
        System.out.println(jedis.get("zyc"));
    }
}

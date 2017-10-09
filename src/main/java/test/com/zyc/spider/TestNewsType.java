package test.com.zyc.spider;

import com.zyc.model.NewsType;
import com.zyc.util.JedisPool;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by YuChen Zhang on 17/09/25.
 */
public class TestNewsType {
    @Test
    public void testGet(){
        System.out.println(NewsType.valueOf("ALLNEWS"));
    }
    @Test
    public void testSpring(){
        Jedis jedis = JedisPool.getJedis();
        System.out.println(jedis.lrange("ALLNEWS",0,-1));
    }
}

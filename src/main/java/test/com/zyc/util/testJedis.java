package test.com.zyc.util;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by YuChen Zhang on 17/10/12.
 */
public class testJedis {
    @Test
    public void testGetRedis(){
        Jedis jedis = new Jedis("123.206.8.180");
        jedis.auth("199616");
        jedis.set("111","11111");
        System.out.println(jedis.get("111"));;
    }
}

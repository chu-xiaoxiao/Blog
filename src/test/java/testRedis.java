import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by YuChen Zhang on 17/09/07.
 */
public class testRedis {
    @Test
    public void testredis(){
        Jedis jedis = new Jedis("123.206.8.180");
        jedis.set("zyc","10160");
        System.out.println(jedis.get("zyc"));
    }
}

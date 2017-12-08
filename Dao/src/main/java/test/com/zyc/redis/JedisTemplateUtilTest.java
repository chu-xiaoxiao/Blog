package test.com.zyc.redis; 

import com.zyc.redis.JedisTemplateUtil;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* JedisTemplateUtil Tester. 
* 
* @author <Authors name> 
* @since <pre>十二月 7, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"classpath*:spring-redis.xml"})
public class JedisTemplateUtilTest { 
    @Autowired
    JedisTemplateUtil jedisTemplateUtil;
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: setRedisTemplate(RedisTemplate<String, Object> redisTemplate) 
* 
*/ 
@Test
public void testSetRedisTemplate() throws Exception {
    System.out.println(jedisTemplateUtil.range("guowai",0,-1).size());
//TODO: Test goes here... 
} 

/** 
* 
* Method: setRedisTemplateSerializable(RedisTemplate<Serializable, Serializable> redisTemplateSerializable) 
* 
*/ 
@Test
public void testSetRedisTemplateSerializable() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setCacheObject(String key, Object value) 
* 
*/ 
@Test
public void testSetCacheObject() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCacheObject(String key) 
* 
*/ 
@Test
public void testGetCacheObject() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setCacheList(String key, List<Object> dataList) 
* 
*/ 
@Test
public void testSetCacheList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCacheList(String key) 
* 
*/ 
@Test
public void testGetCacheList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: range(String key, long start, long end) 
* 
*/ 
@Test
public void testRange() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: listSize(String key) 
* 
*/ 
@Test
public void testListSize() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: listSet(String key, int index, Object obj) 
* 
*/ 
@Test
public void testListSet() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: leftPush(String key, Object obj) 
* 
*/ 
@Test
public void testLeftPush() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: rightPush(String key, Object obj) 
* 
*/ 
@Test
public void testRightPush() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: trim(String key, int start, int end) 
* 
*/ 
@Test
public void testTrim() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: remove(String key, long i, Object obj) 
* 
*/ 
@Test
public void testRemove() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setCacheSet(String key, Set<Object> dataSet) 
* 
*/ 
@Test
public void testSetCacheSet() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCacheSet(String key) 
* 
*/ 
@Test
public void testGetCacheSet() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setCacheMap(String key, Map<String, Object> dataMap) 
* 
*/ 
@Test
public void testSetCacheMap() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCacheMap(String key) 
* 
*/ 
@Test
public void testGetCacheMap() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setCacheIntegerMap(String key, Map<Integer, Object> dataMap) 
* 
*/ 
@Test
public void testSetCacheIntegerMap() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCacheIntegerMap(String key) 
* 
*/ 
@Test
public void testGetCacheIntegerMap() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteMap(String key) 
* 
*/ 
@Test
public void testDeleteMap() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: expire(String key, long time, TimeUnit unit) 
* 
*/ 
@Test
public void testExpire() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: increment(String key, long step) 
* 
*/ 
@Test
public void testIncrement() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: del(final byte[] key, final Integer select) 
* 
*/ 
@Test
public void testDelForKeySelect() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: get(final byte[] key, final Integer select) 
* 
*/ 
@Test
public void testGet() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: set(final byte[] key, final byte[] value, final long liveTime, final Integer select) 
* 
*/ 
@Test
public void testSet() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: clear(final Integer select) 
* 
*/ 
@Test
public void testClear() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: size(final Integer select) 
* 
*/ 
@Test
public void testSize() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: keys(byte[] p, final Integer select) 
* 
*/ 
@Test
public void testKeys() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getTtl(byte[] key, final Integer select) 
* 
*/ 
@Test
public void testGetTtl() throws Exception { 
//TODO: Test goes here... 
} 


} 

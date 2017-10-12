package test.com.zyc.spider; 

import com.zyc.spider.NewsSpider;
import com.zyc.spider.TodayInHistorySpider;
import com.zyc.util.HttpclientUtil;
import com.zyc.util.JedisPoolUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import redis.clients.jedis.Jedis;

import java.io.IOException;
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
        Jedis jedis = JedisPoolUtil.getJedis();
        jedis.set("zyc","1016");
        System.out.println(jedis.get("zyc"));
    }
    @Test
    public void testSinaUrl() throws IOException {
        HttpclientUtil httpclientUtil = new HttpclientUtil();
        String result = httpclientUtil.getDocumentFromUriPost("http://news.sina.com.cn/hotnews/");
        Document document = new Document(result);
        Elements elements= document.getElementsByTag("script");
        for(Element temp :elements){
            System.out.println(temp);
        }
    }
    @Test
    public void testGetNes() throws IOException {
        NewsSpider newsSpider = new NewsSpider();
        System.out.println("============================");
    }
    @Test
    public void testGetJs() throws IOException {
        HttpclientUtil httpclientUtil = new HttpclientUtil();
        String result = httpclientUtil.getDocumentFromUriGet("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50746.json?_=1507532502499");
        System.out.println(result);
    }
}

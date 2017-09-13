package test.com.zyc.spider; 

import com.zyc.spider.TodayInHistorySpider;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.Map;

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
}

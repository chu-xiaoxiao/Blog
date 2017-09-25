package test.com.zyc.spider;

import com.zyc.model.NewsType;
import org.junit.Test;

/**
 * Created by YuChen Zhang on 17/09/25.
 */
public class TestNewsType {
    @Test
    public void testGet(){
        System.out.println(NewsType.valueOf("ALLNEWS"));
    }
}

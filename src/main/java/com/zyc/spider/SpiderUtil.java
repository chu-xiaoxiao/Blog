package com.zyc.spider;

import com.zyc.jedis.JedisPoolUtil1;
import com.zyc.util.MyException;
import com.zyc.util.SpringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YuChen Zhang on 17/09/13.
 */
public class SpiderUtil {
    private static Logger logger = LogManager.getLogger(SpiderUtil.class);

    private static JedisPoolUtil1 jedisPoolUtil1;
    static{
        jedisPoolUtil1 = (JedisPoolUtil1) SpringUtil.getBean("jedisPoolUtil1");
    }


    /**
     * 校验Redis中存储的日期是否和服务器一致
     * 不一致返回false
     * @return
     */
    public static Boolean validateDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd zzzz");
        //判断Redis中的日期是否与当前日期一致
        if(simpleDateFormat.format(new Date()).equals(jedisPoolUtil1.get("date"))){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 刷新当前Redis中存放的时间戳
     * @return
     */
    public static Boolean flushDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd zzzz");
        jedisPoolUtil1.set("date",simpleDateFormat.format(date));
        logger.info("时间有差异刷新当前Redis中的时间戳"+simpleDateFormat.format(date));
        return true;
    }

    /**
     * 刷新时间的同时刷新所有跟时间有关的纪录
     * @return
     */
    public static Boolean flushDateAndData() throws IOException, MyException {
        NewsSpider newsSpider = new NewsSpider();
        TodayInHistorySpider todayInHistorySpider = new TodayInHistorySpider();
        flushDate();
        newsSpider.getNewsFromSinaToRedis();
        todayInHistorySpider.setHistroyToRedis();
        return true;
    }
}

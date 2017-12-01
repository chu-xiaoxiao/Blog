package com.zyc.timmer;

import com.zyc.jedis.JedisTemplateUtil;
import com.zyc.spider.NewsSpider;
import com.zyc.spider.SpiderUtil;
import com.zyc.util.MyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.TimerTask;

/**
 * Created by YuChen Zhang on 17/09/26.
 */
@Component
public class GetNewsTimmer extends TimerTask {
    private static Logger logger = LogManager.getLogger(GetNewsTimmer.class);
    private static boolean isRunning = false;

    @Autowired
    NewsSpider newsSpider;

    public static boolean isIsRunning() {
        return isRunning;
    }

    public static void setIsRunning(boolean isRunning) {
        GetNewsTimmer.isRunning = isRunning;
    }

    @Override
    public void run() {
        if(!isRunning){
            logger.info("获取新闻>>>>>任务开启");
            isRunning=true;
            try {
                newsSpider.getNewsFromSinaToRedis();
                isRunning=false;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MyException e) {
                e.printStackTrace();
            }
            logger.info("获取新闻>>>>>任务结束");
        }else{
            logger.error("上一次获取新闻任务还未结束");
        }
    }
}

package com.zyc.timmer;

import com.zyc.spider.NewsSpider;
import com.zyc.spider.SpiderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

/**
 * Created by YuChen Zhang on 17/09/26.
 */
@Component
public class RedisFlushTimmer extends TimerTask {
    private static Logger logger = LogManager.getLogger(RedisFlushTimmer.class);
    private static boolean isRunning = false;

    @Autowired
    NewsSpider newsSpider;

    public static boolean isIsRunning() {
        return isRunning;
    }

    public static void setIsRunning(boolean isRunning) {
        RedisFlushTimmer.isRunning = isRunning;
    }
    @Autowired
    SpiderUtil spiderUtil;

    @Override
    public void run() {
        if (!isRunning) {
            logger.info("Redis刷新>>>>>任务开启");
            isRunning = true;
            newsSpider = new NewsSpider();
                spiderUtil.flushDate();
                isRunning = false;
            logger.info("Redis刷新>>>>>任务结束");
        } else {
            logger.error("上一次获Redis刷新任务还未结束");
        }
    }
}
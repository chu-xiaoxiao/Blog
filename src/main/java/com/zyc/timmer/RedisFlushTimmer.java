package com.zyc.timmer;

import com.zyc.spider.NewsSpider;
import com.zyc.spider.SpiderUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Spider;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.IOException;
import java.util.TimerTask;

/**
 * Created by YuChen Zhang on 17/09/26.
 */
public class RedisFlushTimmer extends TimerTask {
    Logger logger = LogManager.getLogger(RedisFlushTimmer.class);
    private ServletContext servletContext;
    private static boolean isRunning = false;

    public RedisFlushTimmer(ServletContext ServletContext) {
        this.servletContext = servletContext;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public static boolean isIsRunning() {
        return isRunning;
    }

    public static void setIsRunning(boolean isRunning) {
        RedisFlushTimmer.isRunning = isRunning;
    }

    @Override
    public void run() {
        if (!isRunning) {
            logger.info("Redis刷新任务开启");
            isRunning = true;
            NewsSpider newsSpider = new NewsSpider();
                SpiderUtil.flushDate();
                isRunning = false;
            logger.info("Redis刷新任务结束");
        } else {
            logger.error("上一次获Redis刷新任务还未结束");
        }
    }
}
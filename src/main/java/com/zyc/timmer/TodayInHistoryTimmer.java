package com.zyc.timmer;

import com.zyc.spider.NewsSpider;
import com.zyc.spider.TodayInHistorySpider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.TimerTask;

/**
 * Created by YuChen Zhang on 17/09/26.
 */
public class TodayInHistoryTimmer extends TimerTask {
    Logger logger = LogManager.getLogger(TodayInHistoryTimmer.class);
    private ServletContext servletContext;
    private static boolean isRunning = false;

    public TodayInHistoryTimmer(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void run() {
        if(!isRunning){
            logger.info("获取历史上的今天>>>>>任务开启");
            isRunning=true;
            TodayInHistorySpider todayInHistorySpider = new TodayInHistorySpider();
            try {
                todayInHistorySpider.setHistroyToRedis();
                isRunning=false;
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info("获取历史上的今天>>>>>任务结束");
        }else{
            logger.error("上一次获取历史上的今天任务还未结束");
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
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
        TodayInHistoryTimmer.isRunning = isRunning;
    }
}

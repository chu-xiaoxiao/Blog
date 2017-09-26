package com.zyc.timmer;

import com.zyc.spider.SpiderUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Calendar;
import java.util.Timer;

/**
 * Created by YuChen Zhang on 17/09/26.
 */
public class MainLinstener implements ServletContextListener {
    private Timer timer = new Timer(true);;
    Logger logger = LogManager.getLogger(MainLinstener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        timer =  new Timer(true);
        this.startTodayInHistoryTimmer(sce);
        this.startNewsTimmer(sce);
        this.startFlushRedisDateTimmer(sce);
    }
    public void startNewsTimmer(ServletContextEvent sce){
        logger.info("定时器开启>>>>>>>>>>新闻获取");
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int newsPeriod = 1000*60*60;
        timer.schedule(new GetNewsTimmer(sce.getServletContext()),0,newsPeriod);
        logger.info("加入定时器任务栈<<<<<<<<新闻获取定时器");
    }
    public void startTodayInHistoryTimmer(ServletContextEvent sce){
        logger.info("定时器开启>>>>>>>>>>历史上的今天获取");
        Calendar calendar = Calendar.getInstance();
        int todayInHistoryPeriod = 1000*60*60*24;
        timer.schedule(new TodayInHistoryTimmer(sce.getServletContext()),0,todayInHistoryPeriod);
        logger.info("加入定时器任务栈<<<<<<<<历史上的今天获取定时器");
    }
    public void startFlushRedisDateTimmer(ServletContextEvent sce){
        logger.info("定时器开启>>>>>>>>>>Redis刷新");
        int redisFlushPeriod = 1000*60*60;
        SpiderUtil.flushDate();
        timer.schedule(new RedisFlushTimmer(sce.getServletContext()),0,redisFlushPeriod);
        logger.info("加入定时器任务栈<<<<<<<<Redis刷新定时器");
    }
}

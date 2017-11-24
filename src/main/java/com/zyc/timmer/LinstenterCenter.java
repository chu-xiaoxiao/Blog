package com.zyc.timmer;

import com.zyc.spider.SpiderUtil;
import com.zyc.spider.TodayInHistorySpider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Calendar;
import java.util.Timer;

/**
 * Created by YuChen Zhang on 17/09/26.
 */
public class LinstenterCenter implements ServletContextListener {
    private Timer timer = new Timer(true);;
    private static Logger logger = LogManager.getLogger(LinstenterCenter.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        timer =  new Timer(true);
        this.startTodayInHistoryTimmer(sce);
        this.startNewsTimmer(sce);
        this.startFlushRedisDateTimmer(sce);
        this.startRedisLoginFlushTimmer(sce);
    }
    private void startNewsTimmer(ServletContextEvent sce){
        logger.info("定时器开启>>>>>>>>>>新闻获取");
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int newsPeriod = 1000*60*60;
        timer.schedule(new GetNewsTimmer(sce.getServletContext()),0,newsPeriod);
        logger.info("加入定时器任务栈<<<<<<<<新闻获取定时器");
    }
    private void startTodayInHistoryTimmer(ServletContextEvent sce){
        logger.info("定时器开启>>>>>>>>>>历史上的今天获取");
        Calendar calendar = Calendar.getInstance();
        int todayInHistoryPeriod = 1000*60*60;
        timer.schedule(new TodayInHistoryTimmer(sce.getServletContext()),0,todayInHistoryPeriod);
        logger.info("加入定时器任务栈<<<<<<<<历史上的今天获取定时器");
    }
    private void startFlushRedisDateTimmer(ServletContextEvent sce){
        logger.info("定时器开启>>>>>>>>>>Redis刷新");
        int redisFlushPeriod = 1000*60*60;
        SpiderUtil.flushDate();
        timer.schedule(new RedisFlushTimmer(sce.getServletContext()),0,redisFlushPeriod);
        logger.info("加入定时器任务栈<<<<<<<<Redis刷新定时器");
    }
    private void startRedisLoginFlushTimmer(ServletContextEvent sce){
        logger.info("定时器开启>>>>>>>>>>key清扫");
        int redisFlushPeriod = 1000*60;
        timer.schedule(new RedisLoginFlushTimmer(sce.getServletContext()),0,redisFlushPeriod);
        logger.info("加入定时器任务栈<<<<<<<<key清扫");
    }
}

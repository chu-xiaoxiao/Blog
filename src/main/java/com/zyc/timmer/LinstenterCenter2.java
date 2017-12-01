/*
package com.zyc.timmer;

import com.zyc.spider.SpiderUtil;
import com.zyc.spider.TodayInHistorySpider;
import com.zyc.util.SpringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Calendar;
import java.util.Timer;

*/
/**
 * Created by YuChen Zhang on 17/09/26.
 *//*

public class LinstenterCenter implements ServletContextListener {
    private Timer timer = new Timer(true);;
    private static Logger logger = LogManager.getLogger(LinstenterCenter.class);

    private static WebApplicationContext applicationContext;
    private GetNewsTimmer getNewsTimmer;
    private RedisFlushTimmer redisFlushTimmer;
    private RedisLoginFlushTimmer redisLoginFlushTimmer;
    private TodayInHistoryTimmer todayInHistoryTimmer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext context = (ApplicationContext) sce.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        getNewsTimmer = (GetNewsTimmer) context.getBean("getNewsTimmer");
        redisFlushTimmer = (RedisFlushTimmer) context.getBean("redisFlushTimmer");
        redisLoginFlushTimmer = (RedisLoginFlushTimmer) context.getBean("redisLoginFlushTimmer");
        todayInHistoryTimmer = (TodayInHistoryTimmer) context.getBean("todayInHistoryTimmer");
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
        timer.schedule(getNewsTimmer,0,newsPeriod);
        logger.info("加入定时器任务栈<<<<<<<<新闻获取定时器");
    }
    private void startTodayInHistoryTimmer(ServletContextEvent sce){
        logger.info("定时器开启>>>>>>>>>>历史上的今天获取");
        Calendar calendar = Calendar.getInstance();
        int todayInHistoryPeriod = 1000*60*60;
        timer.schedule(todayInHistoryTimmer,0,todayInHistoryPeriod);
        logger.info("加入定时器任务栈<<<<<<<<历史上的今天获取定时器");
    }
    private void startFlushRedisDateTimmer(ServletContextEvent sce){
        logger.info("定时器开启>>>>>>>>>>Redis刷新");
        int redisFlushPeriod = 1000*60*60;
        new SpiderUtil().flushDate();
        timer.schedule(redisFlushTimmer,0,redisFlushPeriod);
        logger.info("加入定时器任务栈<<<<<<<<Redis刷新定时器");
    }
    private void startRedisLoginFlushTimmer(ServletContextEvent sce){
        logger.info("定时器开启>>>>>>>>>>key清扫");
        int redisFlushPeriod = 1000*60;
        timer.schedule(redisLoginFlushTimmer,0,redisFlushPeriod);
        logger.info("加入定时器任务栈<<<<<<<<key清扫");
    }
}
*/

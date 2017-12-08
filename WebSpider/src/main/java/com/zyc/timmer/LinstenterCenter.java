package com.zyc.timmer;

import com.zyc.spider.SpiderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Timer;

/**
 * Created by YuChen Zhang on 17/09/26.
 */
@Component
public class LinstenterCenter implements ApplicationListener<ContextRefreshedEvent> {
    private Timer timer = new Timer(true);;
    private static Logger logger = LogManager.getLogger(LinstenterCenter.class);

    @Autowired
    private GetNewsTimmer getNewsTimmer;
    @Autowired
    private RedisFlushTimmer redisFlushTimmer;
    @Autowired
    private RedisLoginFlushTimmer redisLoginFlushTimmer;
    @Autowired
    private TodayInHistoryTimmer todayInHistoryTimmer;
    @Autowired
    SpiderUtil spiderUtil;

    private void startNewsTimmer(){
        logger.info("定时器开启>>>>>>>>>>新闻获取");
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int newsPeriod = 1000*60*60;
        timer.schedule(getNewsTimmer,0,newsPeriod);
        logger.info("加入定时器任务栈<<<<<<<<新闻获取定时器");
    }
    private void startTodayInHistoryTimmer(){
        logger.info("定时器开启>>>>>>>>>>历史上的今天获取");
        Calendar calendar = Calendar.getInstance();
        int todayInHistoryPeriod = 1000*60*60;
        timer.schedule(todayInHistoryTimmer,0,todayInHistoryPeriod);
        logger.info("加入定时器任务栈<<<<<<<<历史上的今天获取定时器");
    }
    private void startFlushRedisDateTimmer(){
        logger.info("定时器开启>>>>>>>>>>Redis刷新");
        int redisFlushPeriod = 1000*60*60;
        timer.schedule(redisFlushTimmer,0,redisFlushPeriod);
        logger.info("加入定时器任务栈<<<<<<<<Redis刷新定时器");
    }
    private void startRedisLoginFlushTimmer(){
        logger.info("定时器开启>>>>>>>>>失效key清扫");
        int redisFlushPeriod = 1000*60;
        timer.schedule(redisLoginFlushTimmer,0,redisFlushPeriod);
        logger.info("加入定时器任务栈<<<<<<<<失效key清扫");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null) {
            timer = new Timer(true);
            this.startTodayInHistoryTimmer();
            this.startNewsTimmer();
            this.startFlushRedisDateTimmer();
            this.startRedisLoginFlushTimmer();
        }
    }
}

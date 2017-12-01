package com.zyc.timmer;

import com.zyc.spider.TodayInHistorySpider;
import com.zyc.util.SpringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.TimerTask;

/**
 * Created by YuChen Zhang on 17/09/26.
 */
@Component
public class TodayInHistoryTimmer extends TimerTask {
    private static Logger logger = LogManager.getLogger(TodayInHistoryTimmer.class);
    private static boolean isRunning = false;

    @Autowired
    TodayInHistorySpider todayInHistorySpider;

    @Override
    public void run() {
        if(!isRunning){
            logger.info("获取历史上的今天>>>>>任务开启");
            isRunning=true;
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

    public static boolean isIsRunning() {
        return isRunning;
    }

    public static void setIsRunning(boolean isRunning) {
        TodayInHistoryTimmer.isRunning = isRunning;
    }
}

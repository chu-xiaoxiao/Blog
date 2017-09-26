package com.zyc.timmer;

import com.zyc.spider.NewsSpider;
import com.zyc.spider.SpiderUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.TimerTask;

/**
 * Created by YuChen Zhang on 17/09/26.
 */
public class GetNewsTimmer extends TimerTask {
    Logger logger = LogManager.getLogger(GetNewsTimmer.class);
    private ServletContext servletContext;
    private static boolean isRunning = false;

    public GetNewsTimmer(ServletContext servletContext) {
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
        GetNewsTimmer.isRunning = isRunning;
    }

    @Override
    public void run() {
        if(!isRunning){
            logger.info("获取新闻>>>>>任务开启");
            isRunning=true;
            NewsSpider newsSpider = new NewsSpider();
            try {
                newsSpider.getNewsFromSinaToRedis();
                isRunning=false;
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info("获取新闻>>>>>任务结束");
        }else{
            logger.error("上一次获取新闻任务还未结束");
        }
    }
}

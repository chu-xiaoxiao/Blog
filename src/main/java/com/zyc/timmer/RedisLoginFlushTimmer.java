package com.zyc.timmer;

import com.zyc.jedis.JedisPoolUtil1;
import com.zyc.util.SpringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.Set;
import java.util.TimerTask;

/**
 * Created by YuChen Zhang on 17/11/23.
 */
public class RedisLoginFlushTimmer extends TimerTask {
    private static Logger logger = LogManager.getLogger(TodayInHistoryTimmer.class);


    private ServletContext servletContext;
    private static boolean isRunning = false;

    JedisPoolUtil1 jedisPoolUtil1;


    public RedisLoginFlushTimmer(ServletContext servletContext) {
        this.servletContext = servletContext;
        ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.jedisPoolUtil1 = (JedisPoolUtil1) ac1.getBean("jedisPoolUtil1");
    }
    @Override
    public void run() {
        if(!isRunning) {
            Integer count = 0;
            logger.info("key清扫>>>>>任务开启");
            Set<byte[]> keys = jedisPoolUtil1.allKeys("*".getBytes(), 1);
            for (byte[] temp : keys) {
                if (jedisPoolUtil1.getTtl(temp, 1) == -1) {
                    jedisPoolUtil1.del(temp, 1);
                    logger.info(new String(temp) + "过期删除");
                    count++;
                }
            }
            logger.info("key清扫>>>>>任务结束");
            logger.info("key清扫>>>>>删除失效key"+count+"个");
        }else{
            logger.error("上一次key清扫任务还未结束");
        }
    }
}

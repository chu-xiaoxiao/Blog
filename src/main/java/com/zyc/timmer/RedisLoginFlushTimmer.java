package com.zyc.timmer;

import com.zyc.jedis.JedisTemplateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.util.Set;
import java.util.TimerTask;

/**
 * Created by YuChen Zhang on 17/11/23.
 */
@Component
public class RedisLoginFlushTimmer extends TimerTask {
    private static Logger logger = LogManager.getLogger(TodayInHistoryTimmer.class);


    private ServletContext servletContext;
    private static boolean isRunning = false;

    @Autowired

    private JedisTemplateUtil jedisTemplateUtil;;

    @Override
    public void run() {
        if(!isRunning) {
            Integer count = 0;
            logger.info("key清扫>>>>>任务开启");
            Set<byte[]> keys = jedisTemplateUtil.keys("*".getBytes(),0);
            for (byte[] temp : keys) {
                if (jedisTemplateUtil.getTtl(temp,1) == -1) {
                    jedisTemplateUtil.del(temp,1);
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

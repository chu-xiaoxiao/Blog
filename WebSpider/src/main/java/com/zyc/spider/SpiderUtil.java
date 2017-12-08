package com.zyc.spider;

import com.zyc.redis.JedisTemplateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YuChen Zhang on 17/09/13.
 */
@Service
public class SpiderUtil {
    private static Logger logger = LogManager.getLogger(SpiderUtil.class);

    private JedisTemplateUtil jedisTemplateUtil;

    @Resource
    public void setJedisTemplateUtil(JedisTemplateUtil jedisTemplateUtil) {
        this.jedisTemplateUtil = jedisTemplateUtil;
    }

    /**
     * 校验Redis中存储的日期是否和服务器一致
     * 不一致返回false
     * @return
     */
    public Boolean validateDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd zzzz");
        //判断Redis中的日期是否与当前日期一致
        if(simpleDateFormat.format(new Date()).equals(jedisTemplateUtil.getCacheObject("date"))){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 刷新当前Redis中存放的时间戳
     * @return
     */
    public Boolean flushDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd zzzz");
        jedisTemplateUtil.setCacheObject("date",simpleDateFormat.format(date));
        logger.info("时间有差异刷新当前Redis中的时间戳"+simpleDateFormat.format(date));
        return true;
    }
}

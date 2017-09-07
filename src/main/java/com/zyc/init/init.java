package com.zyc.init;

import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YuChen Zhang on 17/09/07.
 */
public class init{
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        Jedis jedis = new Jedis("123.206.8.180");
        //初始化redis中时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        jedis.set("date",simpleDateFormat.format(date));
    }
}

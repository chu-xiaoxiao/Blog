package com.zyc.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by YuChen Zhang on 17/11/24.
 */
public class SpringUtil implements ApplicationContextAware {// extends
    // ApplicationObjectSupport{

    private static ApplicationContext context = null;
    private static SpringUtil stools = null;

    public synchronized static SpringUtil init() {
        if (stools == null) {
            stools = new SpringUtil();
        }
        return stools;
    }

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
    }

    public synchronized static Object getBean(String beanName) {
        return context.getBean(beanName);
    }
}
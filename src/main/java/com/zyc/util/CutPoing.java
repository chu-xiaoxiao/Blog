package com.zyc.util;

import com.zyc.mapper.LogrecordMapper;
import com.zyc.model.Logrecord;
import com.zyc.model.User;
import com.zyc.service.LogRecordService;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import java.lang.reflect.Method;

/**
 *
 * Created by YuChen Zhang on 17/10/26.
 */
@Aspect
@Component("cutPoing")
public class CutPoing {
    @Autowired
    @Qualifier("logRecordServiceImplements")
    LogRecordService logRecordService;

    @Pointcut("@annotation(com.zyc.util.LogAop)")
    public void controllerAspect(){

    }

    @Around( "execution(* com.zyc.service.*.* (..))")
    public Object doAround(ProceedingJoinPoint joinpoint){
        System.out.println("日志aop开始========");
        Object result = null;
        try {
             result = joinpoint.proceed();
            System.out.println("日志aop结束======");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    @Around("execution(* com.zyc.service.*.* (..))&&@annotation(logAop)")
    public Object  doAroundWithParam(ProceedingJoinPoint joinPoint,LogAop logAop){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Logrecord logrecord = new Logrecord();
        Object result = null;
        try {
            User user = (User) request.getSession().getAttribute("user");
            logrecord.setAction(logAop.CRDU().getCRDU());
            logrecord.setIp(IPUtils.getIpaddr(request));
            logrecord.setLogrecord(logAop.logRecord());
            logrecord.setTablename(logAop.tableName());
            if(user!=null) {
                logrecord.setUserid(user.getId());
                logrecord.setUsername(user.getUsername());
            }
            logRecordService.insert(logrecord);
            result = joinPoint.proceed();
            System.out.println(request.getRequestURL());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    public static void getValue(Joinpoint joinpoint,String key){

    }
}

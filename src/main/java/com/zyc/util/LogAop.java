package com.zyc.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.*;

/**
 * 日志切面类
 * Created by YuChen Zhang on 17/10/26.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAop {
    String ip() default "";
}

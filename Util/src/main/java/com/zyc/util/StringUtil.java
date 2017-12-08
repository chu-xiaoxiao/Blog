package com.zyc.util;

/**
 * Created by YuChen Zhang on 17/10/11.
 */
public class StringUtil {
    /**
     * 判断字符串是否不为空
     * @param temp
     * @return
     */
    public static Boolean judeStringIsNullAndVoid(String temp){
        return temp!=null&&!"".equals(temp)?true:false;
    }
}

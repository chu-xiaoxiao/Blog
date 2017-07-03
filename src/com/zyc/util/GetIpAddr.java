package com.zyc.util;

import javax.servlet.http.HttpServletRequest;

public class GetIpAddr {
	public static String getIpaddr(HttpServletRequest request){
		if(request.getHeader("x-forwarded-for")==null){
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
}

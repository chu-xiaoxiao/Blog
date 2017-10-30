package com.zyc.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


import com.zyc.model.Ip;
import com.zyc.service.IPService;
import com.zyc.util.IPUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Servlet Filter implementation class Charset
 */
@Component("charset")
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/*" }, servletNames = { "springMVC" })
public class Charset implements Filter {

    @Autowired
    @Qualifier("iPServiceImplements")
    private IPService iPservice;

    /**
     * Default constructor. 
     */
    public Charset() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
        Logger logger = LogManager.getLogger(Charset.class);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		HttpServletRequest request1 = (HttpServletRequest) request;
		if(request1.getRequestURL().toString().contains("do")||request1.getRequestURL().toString().contains("action")){
			logger.info("IP:"+IPUtils.getIpaddr(request1)+"\t"+request1.getRequestURL());
		}
		if (request1.getSession().getAttribute("temp") == null) {
			request1.getSession().setAttribute("temp", 1);
			Ip ip = new Ip();
			ip.setIp(request.getRemoteAddr());
			iPservice.addIP(ip);
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}

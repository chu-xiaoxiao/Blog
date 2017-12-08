package com.zyc.filter;

import com.zyc.model.Ip;
import com.zyc.service.IPService;
import com.zyc.util.IPUtils;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * Servlet Filter implementation class Charset
 */
@Component("ipLog")
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/*" }, servletNames = { "springMVC" })
public class Charset implements Filter {
	private static Logger logger = LogManager.getLogger(Charset.class);
    @Autowired
    @Qualifier("iPServiceImplements")
    private IPService iPservice;

    /**
     * Default constructor. 
     */
    public Charset() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest request1 = (HttpServletRequest) request;
		if(request1.getRequestURL().toString().contains("do")||request1.getRequestURL().toString().contains("action")){
			logger.info("IP:"+ IPUtils.getIpaddr(request1)+"\t"+request1.getRequestURL());
		}
		if (request1.getSession().getAttribute("temp") == null) {
			request1.getSession().setAttribute("temp", 1);
			Ip ip = new Ip();
			ip.setIp(request.getRemoteAddr());
			if(!IPUtils.isLoopbackaddress(ip.getIp())) {
				ip.setDate(new Date());
				JSONObject ipLocal = IPUtils.getLocationByGode(ip.getIp());
				ip.setLocation(ipLocal.get("infocode")+","+ipLocal.get("province")+","+ipLocal.get("city"));
				ip.setX(ipLocal.get("rectangle").toString().split(";")[0]);
				ip.setY(ipLocal.get("rectangle").toString().split(";")[1]);
				iPservice.addIP(ip);
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		logger.info("初始化ip记录过滤器");
	}

}

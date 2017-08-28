package com.zyc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter(filterName="UserFilter",urlPatterns={"/Houtai/*"})
public class UserFilter implements Filter {
    /**
     * Default constructor. 
     */
    public UserFilter() {
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
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		HttpSession	session= httpServletRequest.getSession();
		if(session.getAttribute("user")==null){
			if(httpServletRequest.getRequestURI().contains("logIn.do")){
				request.getRequestDispatcher("/user/logIn.do").forward(request, response);
				//httpServletResponse.sendRedirect("/SSM/user/logIn.do");
				return;
			}else if(httpServletRequest.getRequestURI().contains("adduser.do")){
				//httpServletResponse.sendRedirect("/SSM/user/adduser.do");
				request.getRequestDispatcher("/user/adduser.do").forward(request, response);
				return;
			}else if(httpServletRequest.getRequestURI().contains("findByName.do")){
				//httpServletResponse.sendRedirect("/SSM/user/adduser.do");
				request.getRequestDispatcher("/user/findByName.do").forward(request, response);
				return;
			}else{
				request.getRequestDispatcher("/user/logIn.jsp").forward(request, response);
				return;
			}
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

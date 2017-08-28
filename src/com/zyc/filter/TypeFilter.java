package com.zyc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zyc.model.User;

@WebFilter(filterName="UserFilterType",urlPatterns={"/Houtai/*"})
public class TypeFilter implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		User user = (User) request.getSession().getAttribute("user");
		System.out.println();
		if(user!=null){
			if(user.getUsertype()!=3){
				request.setAttribute("msg", "权限不足");
				request.getRequestDispatcher("/user/error.jsp").forward(arg0, arg1);
			}
		}else{
			response.sendRedirect("/SSM/user/logIn.jsp");
			return;
		}
		arg2.doFilter(arg0, arg1);
	}
}

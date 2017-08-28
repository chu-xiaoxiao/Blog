package com.zyc.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zyc.model.User;
import com.zyc.service.UserService;
import com.zyc.service.WenZhangService;
import com.zyc.util.EncodeMD5;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private WenZhangService wenZhangService;
	@RequestMapping(value="/hello.do")
	public String find(HttpServletRequest request){
		String userName = userService.findUsername(2);
		System.out.println(userName);
		System.out.println(userService.findUser(2));
		return "/SSM/index";
	}
	@RequestMapping(value="/user/adduser.do")
	public String add(HttpServletRequest request){
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPssword(EncodeMD5.encodeMD5(request.getParameter("password")));
		user.setUsertype(1);
		userService.insertuUser(user);
		request.getSession().setAttribute("user", user);
		return "redirect:/index.jsp"; 
	}
	@RequestMapping(value="/user/logIn.do")
	public ModelAndView login(HttpServletRequest request){
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPssword(EncodeMD5.encodeMD5(request.getParameter("password")));
		user=userService.logIn(user);
		ModelAndView modelAndView = new ModelAndView();
		if(user==null){
			modelAndView.setViewName("redirect:/user/logIn.jsp");
			return modelAndView;
		}else if(user.getUsertype()==3){
			request.getSession().setAttribute("user", user);
			modelAndView.setViewName("redirect:/Houtai/");
			return modelAndView;
		}
		else{ 
			request.getSession().setAttribute("user", user);
			modelAndView.setViewName("redirect:/index.jsp");
			return modelAndView;
		}
	}
	@RequestMapping(value="/user/modifyTouXiang")
	public ModelAndView modifyTouxiang(@RequestParam(value="touxiang",required=false) MultipartFile file,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		String path = request.getSession().getServletContext().getRealPath("imgs");
		String filename = "touxiang.png";
		File temp = new File(path,filename);
		try {
			file.transferTo(temp);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/WenZhang/findAll.do");
		return modelAndView;
	}
	@RequestMapping(value="/user/findByName")
	public void findByName(HttpServletRequest request,HttpServletResponse response){
		User user = userService.findByName(request.getParameter("username"));
		try {
			PrintWriter out = new PrintWriter(response.getOutputStream());
			if(user!=null){
				out.print("error");
			}else{
				out.print("ok");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
		}
	}
	@RequestMapping(value="/user/logout")
	public ModelAndView logout(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		request.getSession().removeAttribute("user");
		modelAndView.setViewName("/user/logIn");
		return modelAndView;
	}
}

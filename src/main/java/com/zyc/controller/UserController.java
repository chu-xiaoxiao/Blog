package com.zyc.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyc.util.MyException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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
	/*@RequestMapping(value="/user/logIn.do")
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
	}*/
	@RequestMapping(value="/user/logIn.do")
	public ModelAndView login(HttpServletRequest request,ModelAndView modelAndView) throws MyException {
		//封装用户信息
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPssword(EncodeMD5.encodeMD5(request.getParameter("password")));

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),user.getUserpassword());
		usernamePasswordToken.setRememberMe(true);
		try {
			subject.login(usernamePasswordToken);
		} catch (UnknownAccountException e) {
			modelAndView.setViewName("/user/logIn");
			modelAndView.addObject("msg","用户名或密码错误");
			return modelAndView;
		}catch (IncorrectCredentialsException e){
			modelAndView.setViewName("/user/logIn");
            modelAndView.addObject("msg","用户名或密码错误");
            return modelAndView;
		}
		//获取登录成功的用户对象
		user = (User) subject.getPrincipal();
		Session session = subject.getSession();
		session.setAttribute("user",user);
		modelAndView.setViewName("/Houtai/index");
		return modelAndView;
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
	@RequestMapping("/user/uploadTemp.do")
	public void upload(@RequestParam(value="tempTouxiang",required=false) MultipartFile tempTouxiang,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
		  //创建一个通用的多部分解析器   
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求   
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request     
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名   
            Iterator<String> iter = multiRequest.getFileNames();  
            ServletOutputStream output = response.getOutputStream();
            while(iter.hasNext()){  
                //取得上传文件   
                MultipartFile file1 = multiRequest.getFile(iter.next());  
                if(file1 != null){  
                    //取得当前上传文件的文件名称   
                    String myFileName = file1.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在   
                    if(myFileName.trim() !=""){  
                        //重命名上传后的文件名
                        String path =request.getServletContext().getRealPath("imgs")+"/tempTouxiang.png";
                        File pathf = new File(path);  
                        if(!pathf.exists()){
                        	pathf.mkdirs();
                        }
                        path = request.getServletContext().getRealPath("imgs")+"/tempTouxiang.png";
                        File localFile = new File(path);  
                        try {
							file1.transferTo(localFile);
							System.out.println(localFile.getAbsolutePath());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
                        output.print("/SSM/imgs/tempTouxiang.png?T="+new Date().getTime());
                    }  
                }  
            }  
        }
	}
	@RequestMapping("/user/xiugaitouxiang.do")
	public ModelAndView queren(ServletRequest request,ModelAndView modelAndView) throws IOException{
		File file = new File(request.getServletContext().getRealPath("imgs")+"/touxiang.png");
		File file2 = new File(request.getServletContext().getRealPath("imgs")+"/tempTouxiang.png");
		file.delete();
		file = new File(request.getServletContext().getRealPath("imgs")+"/touxiang.png");
		file2.renameTo(file);
		modelAndView.setViewName("redirect:/Houtai/index.jsp");
		return modelAndView;
	}
}

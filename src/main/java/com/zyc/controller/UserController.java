package com.zyc.controller;

import com.zyc.model.Role;
import com.zyc.model.User;
import com.zyc.service.RoleService;
import com.zyc.service.UserService;
import com.zyc.service.WenzhangService;
import com.zyc.util.EncodeMD5;
import com.zyc.util.MailUtil;
import com.zyc.util.MyException;
import com.zyc.util.VerifyCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.security.Security;
import java.util.Date;
import java.util.Iterator;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
    @Qualifier("userServiceImplement")
	private UserService userService;
	@Autowired
	private WenzhangService wenZhangService;

	@Autowired
    @Qualifier("roleServiceImplements")
    RoleService roleService;
    @RequestMapping(value="/hello.do")
	public String find(HttpServletRequest request){
		String userName = userService.findUsername(2);
		System.out.println(userName);
		System.out.println(userService.findUser(2));
		return "redirect :/SSM/index.jsp";
	}
	@RequestMapping(value = "/authorization.do")
    public ModelAndView authorization(ModelAndView modelAndView){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute("user");
        Role role = new Role();
        role.setRoleid(3);
        user = userService.findByName(user.getUsername());
        roleService.authorization(user,role);
        modelAndView.setViewName("redirect:/index.jsp");
        return modelAndView;
    }

	@RequestMapping(value="/adduser.do")
	public ModelAndView add(HttpServletRequest request,ModelAndView modelAndView){
        String verifyCode = request.getParameter("veudyCode_email");
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        if(!session.getAttribute("verifyCode").equals(verifyCode)){
            modelAndView.addObject("addError","验证码错误");
            modelAndView.setViewName("redirect:/user/logIn.jsp");
            return modelAndView;
        }
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setUserpassword(EncodeMD5.encodeMD5(request.getParameter("password")));
        user.setUsermail(request.getParameter("useremail"));
		user.setUsertype(1);
        userService.insertuUser(user);

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),user.getUserpassword());
        usernamePasswordToken.setRememberMe(true);
        subject.login(usernamePasswordToken);

        session.setAttribute("user",user);
        modelAndView.setViewName("redirect:/user/authorization.do");
		return modelAndView;
	}
	@RequestMapping(value="/logIn.do")
	public ModelAndView login(HttpServletRequest request,ModelAndView modelAndView,String veudyCode) throws MyException {
		//封装用户信息
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setUserpassword(EncodeMD5.encodeMD5(request.getParameter("password")));

		Subject subject = SecurityUtils.getSubject();
		//检测验证码是否正确
		Session session = subject.getSession();
		//清除可能存在原有的权限
        session.setAttribute("roles",null);
        session.setAttribute("powers",null);
		String verifyCode = ((String) session.getAttribute("veudyCode")).toLowerCase();
		if(!verifyCode.equals(veudyCode.toLowerCase())){
			modelAndView.setViewName("/user/logIn");
			modelAndView.addObject("msg","验证码错误");
			return modelAndView;
		}

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
		}catch(AuthenticationException e){
			modelAndView.setViewName("/user/logIn");
			modelAndView.addObject("msg","用户名或密码错误");
			return modelAndView;
		}catch(Exception e){
			modelAndView.setViewName("/user/logIn");
			modelAndView.addObject("msg","未知错误,登录失败");
			return modelAndView;
		}
		//获取登录成功的用户对象
		user = (User) subject.getPrincipal();
		session.setAttribute("user",user);
		if(subject.hasRole("administrator")||subject.hasRole("author")) {
            modelAndView.setViewName("redirect:/Houtai/index.jsp");
        }else{
		    modelAndView.setViewName("redirect:/index.jsp");
        }
		return modelAndView;
	}
	@RequestMapping(value="/findByName")
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
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		SecurityUtils.getSubject().logout();

        request.getSession().setAttribute("roles",null);
        request.getSession().setAttribute("powers",null);
		modelAndView.setViewName("redirect:/wenzhang/index.do");
		return modelAndView;
	}

    /**
     * 上传临时头像
     * @param tempTouxiang
     * @param request
     * @param response
     * @throws IllegalStateException
     * @throws IOException
     */
	@RequestMapping("/uploadTemp.do")
	public void upload(@RequestParam(value="tempTouxiang",required=false) MultipartFile tempTouxiang,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{

		  //创建一个通用的多部分解析器
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求   
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request     
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名   
            Iterator<String> iter = multiRequest.getFileNames();  
            ServletOutputStream output = response.getOutputStream();
            String path = "/home/imgs/file/tempTouxiang";
            while(iter.hasNext()){  
                //取得上传文件   
                MultipartFile file1 = multiRequest.getFile(iter.next());  
                if(file1 != null){  
                    //取得当前上传文件的文件名称   
                    String myFileName = file1.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在   
                    if(myFileName.trim() !=""){  
                        //重命名上传后的文件名
                        File pathf = new File(path);  
                        if(!pathf.exists()){
                        	pathf.mkdirs();
                        }
                        File localFile = new File(path+"/"+user.getId()+user.getUsername()+".png");
                        try {
							file1.transferTo(localFile);
							System.out.println(localFile.getAbsolutePath());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
                        output.print(path+"/"+user.getId()+user.getUsername()+".png");
                    }  
                }  
            }  
        }
	}

    /**
     * 通过上传至临时文件夹的头像修改头像文件夹的头像
     * @param request
     * @param modelAndView
     * @return
     * @throws IOException
     */
	@RequestMapping("/xiugaitouxiang.do")
	public ModelAndView queren(ServletRequest request,ModelAndView modelAndView) throws IOException{
        String path = "/home/imgs/file/";
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		File file = new File(path+"touxiang/"+user.getId()+user.getUsername()+".png");
		if(!file.getParentFile().exists()){
		    file.getParentFile().mkdirs();
        }
        //判断当前用户是否有头像，如果没有，新建临时文件
		if(!file.exists()){
		    PrintWriter printWriter = new PrintWriter(new FileOutputStream(file));
		    printWriter.print(1);
		    printWriter.close();
        }
		File file2 = new File(path+"/tempTouxiang/"+user.getId()+user.getUsername()+".png");
		file.delete();
		file = new File(path+"/touxiang/"+user.getId()+user.getUsername()+".png");
		file2.renameTo(file);
		modelAndView.setViewName("redirect:/Houtai/index.jsp");
		return modelAndView;
	}

	@RequestMapping("/getVerifyCode.do")
	public void getVerifyCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String verudyCode = VerifyCodeUtils.generateVerifyCode(4);
        session.setAttribute("veudyCode", verudyCode.toLowerCase());
        Integer x = 100;
        Integer y = 40;
        VerifyCodeUtils.outputImage(x, y, response.getOutputStream(), verudyCode);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
    @RequestMapping("/getVerifyCodeFromMail.do")
    public void getVerifyCodeFromMail(HttpServletRequest request,HttpServletResponse response) throws IOException {
        MailUtil mailUtil = new MailUtil(UserController.class.getClassLoader().getResource("mail.yml"));
        PrintWriter out = response.getWriter();
        String veudyCode = null;
        try {
            veudyCode = mailUtil.sendVerifyCode(10, request.getParameter("useremail"));
        } catch (UnsupportedEncodingException e) {
            out.print("验证码获取失败");
            e.printStackTrace();
        } catch (MessagingException e) {
            out.print("验证码获取失败");
            e.printStackTrace();
        }
        out.print("验证码已发送至您的邮箱");
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("verifyCode",veudyCode);
        out.flush();
        out.close();
    }

    @RequestMapping("/modifyUserinfo.do")
    public ModelAndView modifyUserInfo(User user,ModelAndView modelAndView) throws UnsupportedEncodingException {
	    String nickname = user.getUsernickname();
        userService.modifyUserInfo(user);
        SecurityUtils.getSubject().getSession().setAttribute("user",userService.findUser(user.getId()));
        modelAndView.setViewName("redirect:/Houtai/index.jsp");
        return modelAndView;
    }
}

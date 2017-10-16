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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
        role.setRoleid(1);
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
		String verifyCode = (String) session.getAttribute("veudyCode");
		if(!verifyCode.equals(veudyCode)){
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
		if(subject.hasRole("administrator")) {
            modelAndView.setViewName("redirect:/Houtai/index.jsp");
        }else{
		    modelAndView.setViewName("redirect:/index.jsp");
        }
		return modelAndView;
	}
	@RequestMapping(value="/modifyTouXiang")
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
	@RequestMapping("/xiugaitouxiang.do")
	public ModelAndView queren(ServletRequest request,ModelAndView modelAndView) throws IOException{
		File file = new File(request.getServletContext().getRealPath("imgs")+"/touxiang.png");
		File file2 = new File(request.getServletContext().getRealPath("imgs")+"/tempTouxiang.png");
		file.delete();
		file = new File(request.getServletContext().getRealPath("imgs")+"/touxiang.png");
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
}

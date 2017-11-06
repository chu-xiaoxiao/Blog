package com.zyc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.security.auth.Subject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyc.model.*;
import com.zyc.service.WenzhangService;
import com.zyc.spider.TodayInHistorySpider;
import com.zyc.util.LogAop;
import org.apache.commons.io.FileUtils;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zyc.service.IPService;
import com.zyc.service.JuZiTypeService;
import com.zyc.spider.JuziService;
import com.zyc.spider.NewsSpider;

@Controller
public class WenZhangController {
	@Autowired
	@Qualifier("wenzhangServiceImplements")
	private WenzhangService wenzhangService;

	@Autowired
	@Qualifier("iPServiceImplements")
	private IPService iPService;

	@Autowired
	@Qualifier("juZITypeServiceImplements")
	private JuZiTypeService juZiTypeService;

	@Autowired
	@Qualifier("juZiSpider")
	private JuziService juziService;


	/**
	 * 查看所有文章
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/WenZhang/findAll")
	public String findAll(HttpServletRequest request) {
		request.setAttribute("wenzhang", wenzhangService.findAllWenzhang());
		return "/Houtai/findByPage";
	}


	/**
	 * 文章主页
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */

	@RequestMapping(value = { "/wenzhang/index.do" })
	public ModelAndView finWenZhangByPageIndex(HttpServletRequest request) throws IOException {
		User user = (User) request.getSession().getAttribute("user");
		ModelAndView modelAndView = new ModelAndView();
		/*
		 * 加载配置文件显示主页的图片以及图片对应文字
		 */
		if (request.getRequestURL().toString().contains("index")) {
			Properties properties = new Properties();
			File file = new File("/home/imgs/conf/index.properties");
			if (!file.exists()) {
				System.out.println(file.getAbsolutePath());
				file.getParentFile().mkdirs();
				file.createNewFile();
				properties.put("img_bg_1.jpg", "is How Give We the User New Superpowers");
				properties.put("img_bg_2.jpg", "is How Give We the User New Superpowers");
				properties.put("img_bg_3.jpg", "is How Give We the User New Superpowers");
				properties.store(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"), "by zyc");
			}
			properties.load(new InputStreamReader(new FileInputStream(file), "utf-8"));
			Map<String, String> juzis = new HashMap<String, String>();
			for (Object s : properties.keySet()) {
				juzis.put((String) s, new String(properties.get(s).toString().getBytes(), "utf-8"));
			}
			modelAndView.addObject("juzis", juzis);
		}
		modelAndView.setViewName("/wenzhang/index");
		return modelAndView;
	}
	@RequestMapping("/wenzhang/blogs.do")
	public ModelAndView blogs(HttpServletRequest request,ModelAndView modelAndView){
		User user = (User) request.getSession().getAttribute("user");
		WenzhangExample wenzhangExample = new WenzhangExample();
		WenzhangExample.Criteria criteria = wenzhangExample.createCriteria();
		if(request.getParameter("wenzhangbiaoti")!=null&&!"".equals(request.getParameter("wenzhangbiaoti"))){
			criteria.andWenzhangbiaotiLike("%"+request.getParameter("wenzhangbiaoti")+"%");
		}
		if(request.getParameter("wenzhangleixing")!=null&&!"".equals(request.getParameter("wenzhangleixing"))) {
			criteria.andWenzhangleixingLike("%"+request.getParameter("wenzhangleixing")+"%");
		}
		wenzhangExample.getOredCriteria().add(criteria);
		Page2<Wenzhang,WenzhangExample> page = new Page2<Wenzhang,WenzhangExample>(wenzhangExample,request.getParameter("currentPage"),request.getParameter("sieze"));
		page = wenzhangService.findWenzhangBySearch(page,user);
		modelAndView.addObject("page",page);
		modelAndView.addObject("wenzhangbiaoti",request.getParameter("wenzhangbiaoti"));
		modelAndView.addObject("wenzhangleixing",request.getParameter("wenzhangleixing"));
		modelAndView.setViewName("/wenzhang/blog");
		return modelAndView;
	}
	/**
	 * 某一篇文章的详细内容
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/wenzhang/xiangxi")
	public ModelAndView xiangxi(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		if (request.getParameter("wenzhangid") != null) {
			Wenzhang wenZhang = wenzhangService.findWenzhangByid(Integer.parseInt(request.getParameter("wenzhangid")),user);
			modelAndView.addObject("wenZhang",wenZhang);
		}
		modelAndView.setViewName("/wenzhang/about");
		return modelAndView;
	}
}
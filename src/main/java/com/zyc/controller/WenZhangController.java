package com.zyc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.zyc.model.*;
import com.zyc.service.WenzhangService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zyc.service.IPService;
import com.zyc.service.JuZiTypeService;
import com.zyc.spider.JuziService;

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
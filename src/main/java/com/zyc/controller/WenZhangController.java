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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyc.model.*;
import com.zyc.service.WenzhangService;
import com.zyc.spider.TodayInHistorySpider;
import org.apache.commons.io.FileUtils;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
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

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

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
	 * 添加文章
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/Houtai/addWenZhang.do")
	public ModelAndView addWenZhang(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		Wenzhang wenZhang = new Wenzhang();
		wenZhang.setWenzhangneirong(request.getParameter("wenzhangneirong"));
		wenZhang.setWenzhangbiaoti(request.getParameter("wenzhangbiaoti"));
		wenZhang.setWenzhangchunwenben(request.getParameter("wenzhangchunwenben").trim());
		wenZhang.setWenzhangleixing(request.getParameter("wenzhangleixing"));
		wenzhangService.addWenzhang(wenZhang);
		return new ModelAndView("redirect:/Houtai/findByPage.do");
	}

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
	 * 删除文章
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/Houtai/delete")
	public ModelAndView deleteWenZhang(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("wenzhangid"));
		wenzhangService.deleteWenzhang(id);
		return new ModelAndView("redirect:/Houtai/findByPage.do");
	}

	/**
	 * 修改文章
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/Houtai/modifywenzhangaction")
	public ModelAndView modifyWenZhang(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Wenzhang wenZhang = new Wenzhang();
		wenZhang.setWenzhangneirong(request.getParameter("wenzhangneirong"));
		wenZhang.setWenzhangbiaoti(request.getParameter("wenzhangbiaoti"));
		wenZhang.setWenzhangleixing(request.getParameter("wenzhangleixing"));
		wenZhang.setWenzhangid(Integer.parseInt(request.getParameter("wenzhangid1")));
		wenZhang.setWenzhangchunwenben(request.getParameter("wenzhangchunwenben").trim());
		wenzhangService.modifyWenzhang(wenZhang);
		return new ModelAndView("redirect:/Houtai/findByPage.do");
	}

	/**
	 * 进入文章修改界面
	 * 
	 * @param request
	 * @param printWriter
	 */
	@RequestMapping("/Houtai/modifywenzhang")
	public void findWenZhangByid(HttpServletRequest request, PrintWriter printWriter) {
		Wenzhang wenZhang = wenzhangService.findWenzhangByid(Integer.parseInt(request.getParameter("wenzhangid")));
		JSONObject json = new JSONObject();
		json.put("wenzhang", wenZhang);
		printWriter.print(json.toString());
		printWriter.flush();
		printWriter.close();
	}

	/**
	 * 文章分页查找
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws net.sf.json.JSONException 
	 */
	@RequestMapping("/Houtai/findByPage.**")
	public ModelAndView finWenZhangByPage(HttpServletRequest request, HttpServletResponse response) throws net.sf.json.JSONException, ClientProtocolException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		WenzhangExample wenzhangExample = new WenzhangExample();
		if (request.getSession().getAttribute("temp") == null) {
			request.getSession().setAttribute("temp", 1);
			IP ip = new IP();
			ip.setIp(request.getRemoteAddr());
			iPService.addIP(ip);
		}
		WenzhangExample.Criteria criteria = wenzhangExample.createCriteria();
		if(request.getParameter("wenzhangbiaoti")!=null&&!"".equals(request.getParameter("wenzhangbiaoti"))){
			criteria.andWenzhangbiaotiLike("%"+request.getParameter("wenzhangbiaoti")+"%");
		}
		if(request.getParameter("wenzhangleixing")!=null&&!"".equals(request.getParameter("wenzhangleixing"))) {
			criteria.andWenzhangleixingLike("%"+request.getParameter("wenzhangleixing")+"%");
		}
		wenzhangExample.getOredCriteria().add(criteria);
        Page2<Wenzhang,WenzhangExample> page = new Page2<Wenzhang,WenzhangExample>(wenzhangExample,request.getParameter("currentPage"),request.getParameter("sieze"));
		page = wenzhangService.findWenzhangBySearch(page);
		modelAndView.addObject("page",page);
		modelAndView.addObject("wenzhangbiaoti",request.getParameter("wenzhangbiaoti"));
        modelAndView.addObject("wenzhangleixing",request.getParameter("wenzhangleixing"));
		modelAndView.setViewName("/Houtai/ListWenZhangByPage");
		return modelAndView;
	}

	/**
	 * 文章分页查找
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws net.sf.json.JSONException 
	 */
	@RequestMapping("/Houtai/findByPage2.**")
	public void finWenZhangByPage2(HttpServletRequest request, HttpServletResponse response) throws net.sf.json.JSONException, ClientProtocolException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		WenzhangExample wenzhangExample = new WenzhangExample();
		if (request.getSession().getAttribute("temp") == null) {
			request.getSession().setAttribute("temp", 1);
			IP ip = new IP();
			ip.setIp(request.getRemoteAddr());
			iPService.addIP(ip);
		}
		WenzhangExample.Criteria criteria = wenzhangExample.createCriteria();
		if(request.getParameter("wenzhangbiaoti")!=null&&!"".equals(request.getParameter("wenzhangbiaoti"))){
			criteria.andWenzhangbiaotiLike("%"+request.getParameter("wenzhangbiaoti")+"%");
		}
		if(request.getParameter("wenzhangleixing")!=null&&!"".equals(request.getParameter("wenzhangleixing"))) {
			criteria.andWenzhangleixingLike("%"+request.getParameter("wenzhangleixing")+"%");
		}
		wenzhangExample.getOredCriteria().add(criteria);
        Page2<Wenzhang,WenzhangExample> page = new Page2<Wenzhang,WenzhangExample>(wenzhangExample,request.getParameter("currentPage"),request.getParameter("sieze"));
        page = wenzhangService.findWenzhangBySearch(page);
		modelAndView.addObject("page",page);
		modelAndView.setViewName("/wenzhang/index");
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("lists", page.getLists());
			PrintWriter printWriter = new PrintWriter(response.getOutputStream());
			printWriter.print(jsonObject);
			System.out.println(jsonObject);
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return modelAndView;
	}

	/**
	 * 文章主页
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/wenzhang/index.**", "/wenzhang/blogs.**" })
	public ModelAndView finWenZhangByPageIndex(HttpServletRequest request) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		WenzhangExample wenzhangExample = new WenzhangExample();
		if (request.getSession().getAttribute("temp") == null) {
			request.getSession().setAttribute("temp", 1);
			IP ip = new IP();
			ip.setIp(request.getRemoteAddr());
			iPService.addIP(ip);
		}
		WenzhangExample.Criteria criteria = wenzhangExample.createCriteria();
        if(request.getParameter("wenzhangbiaoti")!=null&&!"".equals(request.getParameter("wenzhangbiaoti"))){
            criteria.andWenzhangbiaotiLike("%"+request.getParameter("wenzhangbiaoti")+"%");
        }
        if(request.getParameter("wenzhangleixing")!=null&&!"".equals(request.getParameter("wenzhangleixing"))) {
			criteria.andWenzhangleixingLike("%"+request.getParameter("wenzhangleixing")+"%");
        }
        wenzhangExample.getOredCriteria().add(criteria);
        Page2<Wenzhang,WenzhangExample> page = new Page2<Wenzhang,WenzhangExample>(wenzhangExample,request.getParameter("currentPage"),request.getParameter("sieze"));
		page = wenzhangService.findWenzhangBySearch(page);
		modelAndView.addObject("page",page);
        modelAndView.addObject("wenzhangbiaoti",request.getParameter("wenzhangbiaoti"));
        modelAndView.addObject("wenzhangleixing",request.getParameter("wenzhangleixing"));
		/*
		 * 加载配置文件显示主页的图片以及图片对应文字
		 */
		if (request.getRequestURL().toString().contains("index")) {
			Properties properties = new Properties();
			/*
			 * properties.load(new
			 * InputStreamReader(request.getServletContext().getResourceAsStream
			 * ("/WEB-INF/index.properties"), "utf-8"));
			 */
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
			modelAndView.setViewName("/wenzhang/index");
			modelAndView.addObject("juzis", juzis);
		}
		if (request.getRequestURL().toString().contains("blog")) {
			modelAndView.setViewName("/wenzhang/blog");
		}
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
		if (request.getParameter("wenzhangid") != null) {
			Wenzhang wenZhang = wenzhangService.findWenzhangByid(Integer.parseInt(request.getParameter("wenzhangid")));
			modelAndView.addObject("wenZhang",wenZhang);
		}
		modelAndView.setViewName("/wenzhang/about");
		return modelAndView;
	}

	/**
	 * 富文本编辑器上传图片
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/Houtai/upLoadImg")
	public void upload(@RequestParam(value = "wangEditorH5File", required = false) MultipartFile file,
	        HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
		        request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			ServletOutputStream output = response.getOutputStream();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file1 = multiRequest.getFile(iter.next());
				if (file1 != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file1.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						// 重命名上传后的文件名
						String fileName = "/" + UUID.randomUUID().toString().replace("-", "") + "."
						        + file1.getOriginalFilename().split("\\.")[1];
						// 定义上传路径
						// String path =
						// request.getServletContext().getRealPath("imgs")+fileName;
						String hash = Calendar.getInstance().get(Calendar.YEAR) + "_"
						        + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "_"
						        + Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
						String path = "/home/imgs/" + hash;
						File pathf = new File(path);
						if (!pathf.exists()) {
							pathf.mkdirs();
						}
						path = "/home/imgs/" + hash + fileName;
						File localFile = new File(path);
						file1.transferTo(localFile);
						// output.print(request.getServletContext().getContextPath()+"/imgs/"+fileName);
						output.print(path);
					}
				}
			}

		}
	}


	/**
	 * 后台主页
	 * 
	 * @param request
	 * @return
	 * @throws ClientProtocolException
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/Houtai/index.do")
	public ModelAndView index(HttpServletRequest request)
	        throws ClientProtocolException, ParseException, IOException {
		Integer ipResultCount = 10;// IP查询的数量
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("count", wenzhangService.countWenzhang());
		modelAndView.addObject("countIp", iPService.countIP(new IPExample()));
		JSONObject listip_date = new JSONObject();
		Jedis jedis = new Jedis("123.206.8.180");
		List<Ip_Date> list = iPService.selectCountByDay(ipResultCount--);
		for (int i = 0; i < ipResultCount / 2 + 1; i++) {
			Ip_Date temp = null;
			temp = list.get(i);
			list.set(i, list.get(ipResultCount - i));
			list.set(ipResultCount - i, temp);
		}
		listip_date.put("list", list);
		modelAndView.addObject("listip_date", listip_date);
		JSONObject typeCount = new JSONObject();
		typeCount.put("typecount", juZiTypeService.getGruop());
		/*//获取每日新闻
		modelAndView.addObject("allNews", new NewsSpider().getNews(NewsType.ALLNEWS));*/
		//获取历史上的今天
		modelAndView.addObject("todayInHistory",new TodayInHistorySpider().getTodayInHistorySpider());
		//获取文章类型计数
		modelAndView.addObject("typecount", typeCount);
		//获取句库计数
		modelAndView.addObject("juziCount", juziService.countJuZiByExample(new JuziExample()));
		//获取当前服务器时间
		modelAndView.addObject("nowDate",jedis.get("date"));
		modelAndView.setViewName("/Houtai/index1");
		return modelAndView;
	}

	/**
	 * 进入修改博客主页的修改页面
	 * 
	 * @param request
	 * @param modelAndView
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/Houtai/modifyJuzi.do")
	public ModelAndView modifyJuzi(HttpServletRequest request, ModelAndView modelAndView) throws IOException {
		File file = new File("/home/imgs/conf/index.properties");
		Properties properties = new Properties();
		properties.load(new InputStreamReader(new FileInputStream(file), "utf-8"));
		Map<String, String> juzis = new HashMap<String, String>();
		for (Object key : properties.keySet()) {
			juzis.put((String) key, new String(properties.get(key).toString().getBytes(), "utf-8"));
		}
		modelAndView.setViewName("/Houtai/modifyJuzi");
		modelAndView.addObject("juzis", juzis);
		return modelAndView;
	}

	/**
	 * 修改主页图片
	 * 
	 * @param request
	 * @param modelAndView
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/Houtai/modifyJuziImg.do")
	public ModelAndView modifyJuziImg(HttpServletRequest request, ModelAndView modelAndView) throws IOException {
		File file = new File("/home/imgs/conf/index.properties");
		Properties properties = new Properties();
		properties.load(new InputStreamReader(new FileInputStream(file), "utf-8"));
		Map<String, String> juzis = new HashMap<String, String>();
		for (Object key : properties.keySet()) {
			juzis.put((String) key, new String(properties.get(key).toString().getBytes(), "utf-8"));
		}
		modelAndView.setViewName("/Houtai/modifyJuzImg");
		modelAndView.addObject("juzis", juzis);
		return modelAndView;
	}

	/**
	 * 修改主页配置文件的动作
	 * 
	 * @param request
	 * @param modelAndView
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/Houtai/modifyJuziaction.do")
	public ModelAndView modifyJuziActioin(HttpServletRequest request, ModelAndView modelAndView) throws IOException {
		File file = new File("/home/imgs/conf/index.properties");
		Map<String, String> juzis = new HashMap<String, String>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			if (name.contains("juzi")) {
				String count = name.substring(7);
				juzis.put("img_bg_" + count, new String(request.getParameter(name).toString().getBytes(), "utf-8"));
			}
		}
		Properties properties = new Properties();
		properties.load(new FileInputStream(file));
		for (Map.Entry<String, String> temp : juzis.entrySet()) {
			properties.setProperty(temp.getKey(), temp.getValue());
		}
		PrintWriter printWriter = new PrintWriter(file, "utf-8");
		properties.store(printWriter, "by ZYC 首页句子以及图片的记录");
		printWriter.close();
		modelAndView.setViewName("redirect:/Houtai/index.do");
		return modelAndView;
	}

	/**
	 * 上传附件
	 * 
	 * @param request
	 * @param response
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/Houtai/addFile.do")
	public void addfile(HttpServletRequest request, HttpServletResponse response)
	        throws IllegalStateException, IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
		        request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			ServletOutputStream output = response.getOutputStream();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file1 = multiRequest.getFile(iter.next());
				if (file1 != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file1.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						// 重命名上传后的文件名
						String fileName = "/" + UUID.randomUUID().toString().replace("-", "") + "."
						        + file1.getOriginalFilename().split("\\.")[1];
						// 定义上传路径
						// String path =
						// request.getServletContext().getRealPath("imgs")+fileName;
						String hash = Calendar.getInstance().get(Calendar.YEAR) + "_"
						        + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "_"
						        + Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
						String path = "/home/imgs/file/" + hash;
						File pathf = new File(path);
						if (!pathf.exists()) {
							pathf.mkdirs();
						}
						path = "/home/imgs/file/" + hash + "/" + myFileName;
						File localFile = new File(path);
						System.out.println(localFile.getAbsolutePath());
						file1.transferTo(localFile);
						// output.print(request.getServletContext().getContextPath()+"/imgs/"+fileName);
						output.print("<a href=/SSM/Houtai/download" + path + ".do?filename=" + myFileName + ">"
						        + myFileName + "</a>");
					}
				}
			}
		}
	}

	/**
	 * 修改主页图片
	 * 
	 * @param request
	 * @param response
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/Houtai/modifyJuziImgAction.do")
	public void modifyJuziImg(HttpServletRequest request, HttpServletResponse response)
	        throws IllegalStateException, IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			ServletOutputStream output = response.getOutputStream();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file1 = multiRequest.getFile(iter.next());
				if (file1 != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file1.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						String path = "/home/imgs/img_bg_" + request.getParameter("id") + ".jpg";
						File pathf = new File(path);
						if (!pathf.exists()) {
							pathf.mkdirs();
						}
						File localFile = new File(path);
						System.out.println(localFile.getAbsolutePath());
						file1.transferTo(localFile);
						output.print("<a href=/SSM/Houtai/download" + path + ".do?filename=" + myFileName + ">"
								+ myFileName + "</a>");
					}
				}
			}
		}
	}
	/**
	 * 下载保存在服务器虚拟路径的文件
	 * 
	 * @param pathbiaoti
	 * @param path
	 * @param request
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/Houtai/download/home/imgs/file/{pathbiaoti}/{path}.*")
	public ResponseEntity<byte[]> download(@PathVariable("pathbiaoti") String pathbiaoti,
	        @PathVariable("path") String path, HttpServletRequest request, @RequestParam("filename") String filename)
	        throws IOException {
		File file2 = new File("/home/imgs/file/" + pathbiaoti + "/" + path);
		String dfileName = new String(filename.getBytes("gb2312"), "iso8859-1");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", dfileName);
		int[] a = new int[10];
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file2), headers, HttpStatus.CREATED);
	}

	/**
	 * 下载保存在服务器端虚拟路径的文件
	 */
	@RequestMapping(value = "/Houtai/download1/**/**.do")
	public ResponseEntity<byte[]> download(HttpServletRequest request, @RequestParam("filename") String filename)
	        throws IOException {
		String path = request.getParameter("filepath");

		File file2 = new File(path);
		String dfileName = new String(filename.getBytes("gb2312"), "iso8859-1");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", dfileName);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file2), headers, HttpStatus.CREATED);
	}

	/**
	 * 获取每日新闻
	 * @param request
	 */
	@RequestMapping(value="/Houtai/getNews/{type}.*")
	public void getnewsByType(HttpServletRequest request,HttpServletResponse response,@PathVariable("type") String type) throws IOException {
		PrintWriter out = response.getWriter();
		NewsSpider newsSpider = new NewsSpider();
		List<String> result = newsSpider.getNews(NewsType.valueOf(type));
		for(String temp : result){
		    out.print(temp);
        }
        out.flush();
        out.close();
	}
}
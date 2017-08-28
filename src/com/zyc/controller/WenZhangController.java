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

import org.apache.commons.io.FileUtils;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.zyc.model.IP;
import com.zyc.model.IPExample;
import com.zyc.model.Ip_Date;
import com.zyc.model.JuziExample;
import com.zyc.model.Page;
import com.zyc.model.WenZhang;
import com.zyc.model.WenZhangSearch;
import com.zyc.service.IPService;
import com.zyc.service.JuZiTypeService;
import com.zyc.service.WenZhangService;
import com.zyc.spider.JuziService;
import com.zyc.spider.NewsSpider;

import net.sf.json.JSONObject;

@Controller
public class WenZhangController {
	@Autowired
	private WenZhangService wenZhangService;

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
		WenZhang wenZhang = new WenZhang();
		wenZhang.setWenzhangneirong(request.getParameter("wenzhangneirong"));
		wenZhang.setWenzhangbiaoti(request.getParameter("wenzhangbiaoti"));
		wenZhang.setWenzhangchunwenben(request.getParameter("wenzhangchunwenben").trim());
		wenZhang.setWenzhangleixing(request.getParameter("wenzhangleixing"));
		wenZhangService.addWenZhang(wenZhang);
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
		request.setAttribute("wenzhang", wenZhangService.findAllWenZhang());
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
		wenZhangService.deleteWenZhang(id);

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
		WenZhang wenZhang = new WenZhang();
		wenZhang.setWenzhangneirong(request.getParameter("wenzhangneirong"));
		wenZhang.setWenzhangbiaoti(request.getParameter("wenzhangbiaoti"));
		wenZhang.setWenzhangleixing(request.getParameter("wenzhangleixing"));
		wenZhang.setId(Integer.parseInt(request.getParameter("wenzhangid1")));
		wenZhang.setWenzhangchunwenben(request.getParameter("wenzhangchunwenben").trim());
		wenZhangService.modifyWenZhang(wenZhang);
		return new ModelAndView("redirect:/Houtai/findByPage.do");
		/*
		 * JSONObject jsonObject = new JSONObject(); jsonObject.put("wenzhang",
		 * wenZhang); PrintWriter printWriter; try { printWriter = new
		 * PrintWriter(response.getOutputStream());
		 * printWriter.println(wenZhang); printWriter.flush();
		 * printWriter.close(); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
	}

	/**
	 * 进入文章修改界面
	 * 
	 * @param request
	 * @param printWriter
	 */
	@RequestMapping("/Houtai/modifywenzhang")
	public void findWenZhangByid(HttpServletRequest request, PrintWriter printWriter) {
		WenZhang wenZhang = wenZhangService.findWenZhangByid(Integer.parseInt(request.getParameter("wenzhangid")));
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
	 */
	@RequestMapping("/Houtai/findByPage.**")
	public ModelAndView finWenZhangByPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		WenZhangSearch wenZhangSearch = new WenZhangSearch();
		if (request.getSession().getAttribute("temp") == null) {
			request.getSession().setAttribute("temp", 1);
			IP ip = new IP();
			ip.setIp(request.getRemoteAddr());
			iPService.addIP(ip);
		}
		if (request.getParameter("wenzhangbiaoti") != null && !"".equals(request.getParameter("wenzhangbiaoti"))) {
			wenZhangSearch.setName(request.getParameter("wenzhangbiaoti"));
		} else {
			wenZhangSearch.setName(null);
		}
		if (request.getParameter("wenzhangleixing") != null && !"".equals(request.getParameter("wenzhangleixing"))) {
			wenZhangSearch.setType(request.getParameter("wenzhangleixing"));
		} else {
			wenZhangSearch.setType(null);
		}
		Page<WenZhang> page = new Page<WenZhang>();
		if (request.getParameter("currentPage") != null) {
			if (page.getAllPage() != null) {
				if (page.getAllPage() < Integer.parseInt(request.getParameter("currentPage"))) {
					page.setCurrentPage(1);
				} else {
					page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
				}
			} else {
				page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
			}
		} else {
			page.setCurrentPage(1);
		}
		if (request.getParameter("sieze") != null) {
			page.setSieze(Integer.parseInt(request.getParameter("sieze")));
		} else {
			page.setSieze(10);
		}
		page.setWenZhangSearch(wenZhangSearch);
		if (page.getCurrentPage() != 0) {
			page.setStatr((page.getCurrentPage() - 1) * page.getSieze());
		} else {
			page.setStatr(0);
		}
		page.setEnd(page.getCurrentPage() * page.getSieze());
		page = wenZhangService.findWenZhangBySearch(page);
		modelAndView.addObject(page);
		modelAndView.setViewName("/Houtai/ListWenZhangByPage");
		/*
		 * try { JSONObject jsonObject = new JSONObject();
		 * jsonObject.put("page", page); PrintWriter printWriter = new
		 * PrintWriter(response.getOutputStream()); printWriter.println(page);
		 * printWriter.flush(); printWriter.close(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
		return modelAndView;
	}

	/**
	 * 文章分页查找
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/Houtai/findByPage2.**")
	public void finWenZhangByPage2(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		WenZhangSearch wenZhangSearch = new WenZhangSearch();
		if (request.getSession().getAttribute("temp") == null) {
			request.getSession().setAttribute("temp", 1);
			IP ip = new IP();
			ip.setIp(request.getRemoteAddr());
			iPService.addIP(ip);
		}
		if (request.getParameter("wenzhangbiaoti") != null && !"".equals(request.getParameter("wenzhangbiaoti"))) {
			wenZhangSearch.setName(request.getParameter("wenzhangbiaoti"));
		} else {
			wenZhangSearch.setName(null);
		}
		if (request.getParameter("wenzhangleixing") != null && !"".equals(request.getParameter("wenzhangleixing"))) {
			wenZhangSearch.setType(request.getParameter("wenzhangleixing"));
		} else {
			wenZhangSearch.setType(null);
		}
		Page<WenZhang> page = new Page<WenZhang>();
		if (request.getParameter("currentPage") != null) {
			if (page.getAllPage() != null) {
				if (page.getAllPage() < Integer.parseInt(request.getParameter("currentPage"))) {
					page.setCurrentPage(1);
				} else {
					page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
				}
			} else {
				page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
			}
		} else {
			page.setCurrentPage(1);
		}
		if (request.getParameter("sieze") != null) {
			page.setSieze(Integer.parseInt(request.getParameter("sieze")));
		} else {
			page.setSieze(4);
		}
		page.setWenZhangSearch(wenZhangSearch);
		if (page.getCurrentPage() != 0) {
			page.setStatr((page.getCurrentPage() - 1) * page.getSieze());
		} else {
			page.setStatr(0);
		}
		page.setEnd(page.getCurrentPage() * page.getSieze());
		page = wenZhangService.findWenZhangBySearch(page);
		modelAndView.addObject(page);
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
		WenZhangSearch wenZhangSearch = new WenZhangSearch();
		if (request.getSession().getAttribute("temp") == null) {
			request.getSession().setAttribute("temp", 1);
			IP ip = new IP();
			ip.setIp(request.getRemoteAddr());
			iPService.addIP(ip);
		}
		if (request.getParameter("wenzhangbiaoti") != null && !"".equals(request.getParameter("wenzhangbiaoti"))) {
			wenZhangSearch.setName(request.getParameter("wenzhangbiaoti"));
		} else {
			wenZhangSearch.setName(null);
		}
		if (request.getParameter("wenzhangleixing") != null && !"".equals(request.getParameter("wenzhangleixing"))) {
			wenZhangSearch.setType(request.getParameter("wenzhangleixing"));
		} else {
			wenZhangSearch.setType(null);
		}
		Page<WenZhang> page = new Page<WenZhang>();
		if (request.getParameter("currentPage") != null) {
			if (page.getAllPage() != null) {
				if (page.getAllPage() < Integer.parseInt(request.getParameter("currentPage"))) {
					page.setCurrentPage(1);
				} else {
					page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
				}
			} else {
				page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
			}
		} else {
			page.setCurrentPage(1);
		}
		if (request.getParameter("sieze") != null) {
			page.setSieze(Integer.parseInt(request.getParameter("sieze")));
		} else {
			if (request.getRequestURL().toString().contains("index")) {
				page.setSieze(4);
			} else {
				page.setSieze(12);
			}
		}
		page.setWenZhangSearch(wenZhangSearch);
		if (page.getCurrentPage() != 0) {
			page.setStatr((page.getCurrentPage() - 1) * page.getSieze());
		} else {
			page.setStatr(0);
		}
		page.setEnd(page.getCurrentPage() * page.getSieze());
		page = wenZhangService.findWenZhangBySearch(page);
		modelAndView.addObject(page);
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
			WenZhang wenZhang = wenZhangService.findWenZhangByid(Integer.parseInt(request.getParameter("wenzhangid")));
			modelAndView.addObject(wenZhang);
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
	 * 随机一篇文章
	 * 
	 * @return
	 */
	@RequestMapping(value = "/wenzhang/randWenZhang")
	public ModelAndView randWenZhang() {
		ModelAndView modelAndView = new ModelAndView();
		List<WenZhang> wenZhangs = wenZhangService.randWenZhang(1);
		modelAndView.addObject(wenZhangs.get(0));
		modelAndView.setViewName("/wenzhang/randpage");
		return modelAndView;
	}

	/**
	 * 后台主页
	 * 
	 * @param request
	 * @return
	 * @throws ClientProtocolException
	 * @throws ParseException
	 * @throws JSONException
	 * @throws IOException
	 */
	@RequestMapping(value = "/Houtai/index.do")
	public ModelAndView index(HttpServletRequest request)
	        throws ClientProtocolException, ParseException, JSONException, IOException {
		Integer ipResultCount = 10;// IP查询的数量
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("count", wenZhangService.countWenzhang());
		modelAndView.addObject("countIp", iPService.countIP(new IPExample()));
		JSONObject listip_date = new JSONObject();
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
		modelAndView.addObject("news", new NewsSpider().getNews());
		modelAndView.addObject("typecount", typeCount);
		modelAndView.addObject("juziCount", juziService.countJuZiByExample(new JuziExample()));
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
}

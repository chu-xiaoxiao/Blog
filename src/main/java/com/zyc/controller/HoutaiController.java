package com.zyc.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zyc.model.IP;
import com.zyc.model.IPExample;
import com.zyc.model.Juzi;
import com.zyc.model.JuziExample;
import com.zyc.model.JuziExample.Criteria;
import com.zyc.model.Page2;
import com.zyc.service.IPService;
import com.zyc.service.JuZiTypeService;
import com.zyc.spider.JuziService;

@Controller("houtaiController")
@RequestMapping("/Houtai")
@RequiresRoles(value="admin")
public class HoutaiController {
	@ExceptionHandler(value = Exception.class)
	public ModelAndView errmsg(ModelAndView modelAndView,Exception exception) {
		modelAndView.setViewName("/Houtai/error/web/index.html");
		modelAndView.addObject("msg",exception.getStackTrace());
		return modelAndView;
	}
	@Autowired
	@Qualifier("iPServiceImplements")
	private IPService iPservice;
	
	@Autowired
	@Qualifier("juZiSpider")
	private JuziService juziservice;
	
	@Autowired
	@Qualifier("juZITypeServiceImplements")
	private JuZiTypeService juZiTypeService;

	/**
	 * 文件树状图
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/filelist.do")
	public void filelist(HttpServletResponse response, HttpServletRequest request) throws IOException {
		PrintWriter out = new PrintWriter(response.getOutputStream());
		String dir = request.getParameter("dir");
		if (dir == null) {
			return;
		}

		if (dir.charAt(dir.length() - 1) == '\\') {
			dir = dir.substring(0, dir.length() - 1) + "/";
		} else if (dir.charAt(dir.length() - 1) != '/') {
			dir += "/";
		}

		dir = java.net.URLDecoder.decode(dir, "UTF-8");

		if (new File(dir).exists()) {
			String[] files = new File(dir).list(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.charAt(0) != '.';
				}
			});
			Arrays.sort(files, String.CASE_INSENSITIVE_ORDER);
			out.print("<ul class=\"jqueryFileTree\" style=\"display: none;\">");
			// All dirs
			for (String file : files) {
				if (new File(dir, file).isDirectory()) {
					out.print(
							"<li class=\"directory collapsed\"><a rel=\""
									+ dir + file + "/\">" + file + "</a></li>");
				}
			}
			// All files
			for (String file : files) {
				if (!new File(dir, file).isDirectory()) {
					int dotIndex = file.lastIndexOf('.');
					String ext = dotIndex > 0 ? file.substring(dotIndex + 1) : "";
					out.print("<li class=\"file ext_" + ext + "\"><a rel=\"" + dir + file + "\">" + file + "</a><a href='/SSM/Houtai/download1"+dir+file+".do?filepath="+dir+file+"&filename="+file.toString()+"'>下载</a><a href='/SSM/Houtai/delete1"+dir+file+".do?filepath="+dir+file+"'>删除</a></li>");
				}
			}
			out.print("</ul>");
			out.flush();
			out.close();
		}
	}
	/**
	 * 删除文件
	 */
	@RequestMapping(value="/delete/home/imgs/**/{pathbiaoti}/{path}.*")
	public ModelAndView download(@PathVariable("pathbiaoti")String pathbiaoti,@PathVariable("path") String path,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView) throws IOException{
	    File file2 = new File("/home/imgs/file/"+pathbiaoti+"/"+path);
	    file2.delete();
	    modelAndView.setViewName("/Houtai/file");
	    return modelAndView;
	}
	/**
	 * 删除文件
	 */
	@RequestMapping(value="/delete1/**/*.do")
	public ModelAndView download1(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView) throws IOException{
		String path = request.getParameter("filepath");
	    File file2 = new File(path);
	    file2.delete();
	    modelAndView.setViewName("/Houtai/file");
	    return modelAndView;
	}
	/**
	 * ip条件查询
	 * @param modelAndView
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/iplist.do")
	public ModelAndView listIP(ModelAndView modelAndView,HttpServletRequest request) throws ParseException{
		IPExample ipExample = new IPExample();
		Integer currentPage = null;
		Integer size = null;
		String mindate=request.getParameter("mindate");
		String mintime=request.getParameter("mintime");
		String maxdate1=request.getParameter("maxdate1");
		String maxtime1 =request.getParameter("maxtime1");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		Date fmindate = null;
		Date fmaxdate = null;
		if(mindate!=null&&!"".equals(mindate)){
			if(mintime==null&&!"".equals(mintime)){
				mintime= "00:00";
				fmindate = dateFormat.parse(mindate+" "+mintime);
			}else{
				fmindate = dateFormat.parse(mindate+" "+mintime);
				ipExample.getOredCriteria().add(ipExample.createCriteria().andDateGreaterThan(fmindate));
			}
		}
		if(maxdate1!=null&&!"".equals(maxdate1)){
			if(maxtime1==null&&!"".equals(maxtime1)){
				maxdate1="23:59";
				fmaxdate = dateFormat.parse(maxdate1+" "+maxtime1);
			}else{
				fmaxdate = dateFormat.parse(maxdate1+" "+maxtime1);
			}
			ipExample.getOredCriteria().get(0).andDateLessThan(fmaxdate);
		}
		if(request.getParameter(("currentpage"))!=null){
			currentPage = Integer.parseInt(request.getParameter("currentpage"));
		}
		if(request.getParameter(("size"))!=null){
			size = Integer.parseInt(request.getParameter("size"));
		}
		Page2<IP	, IPExample> page2 = new Page2<IP, IPExample>(ipExample, currentPage, size);
		page2 = iPservice.findIpByPage(page2);
		modelAndView.addObject("page2",page2);
		modelAndView.setViewName("/Houtai/listip");
		if(mindate!=null){
			modelAndView.addObject("mindate",mindate);
		}
		if(mintime!=null){
			modelAndView.addObject("mintime",mintime);
		}
		if(maxdate1!=null){
			modelAndView.addObject("maxdate1",maxdate1);
		}
		if(maxtime1!=null){
			modelAndView.addObject("maxtime1",maxtime1);
		}
		return modelAndView;
	}
	/**
	 * 爬取句子
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping("/updataJuzi.do")
	public ModelAndView updatajuzi(ModelAndView modelAndView,HttpServletRequest request){
		Integer currentPage = null;
		Integer size = null;
		JuziExample juziExample = new JuziExample();
		String url = request.getParameter("juziurl");
		juziservice.updateJuzi(url);
		if(request.getParameter(("currentpage"))!=null){
			currentPage = Integer.parseInt(request.getParameter("currentpage"));
		}
		if(request.getParameter(("size"))!=null){
			size = Integer.parseInt(request.getParameter("size"));
		}
		Page2<Juzi, JuziExample> page2 = new Page2<Juzi, JuziExample>(juziExample, currentPage, size);
		page2=juziservice.findJuziByPage(page2);
		modelAndView.addObject(page2);
		modelAndView.setViewName("/Houtai/juzi");
		return modelAndView;
	}
	/**
	 * 查看句库
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping("/listjuzi.do") 
	public ModelAndView listjuzi(ModelAndView modelAndView,HttpServletRequest request){
		Integer currentPage = null;
		Integer size = null;
		String juzi = request.getParameter("juzi");
		String juziType = request.getParameter("juzileixing");
		String chuchu = request.getParameter("chuchu");
		if(request.getParameter("currentpage")!=null&&!"".equals(request.getParameter("currentpage").trim())){
			currentPage = Integer.parseInt(request.getParameter("currentpage"));
		}
		if(request.getParameter("size")!=null&&!"".equals(request.getParameter("size").trim())){
			size = Integer.parseInt(request.getParameter("size"));
		}
		JuziExample juziExample = new JuziExample();
		Criteria criteria = juziExample.createCriteria();
		juziExample.getOredCriteria().set(0, juziExample.createCriteria());
		if(juzi!=null&&!"".equals(juzi)){
			criteria=criteria.andJuzineirongLike("%"+juzi+"%");
			currentPage=0;
		}
		if(juziType!=null&&!"".equals(juziType)){
			Integer temp = Integer.parseInt(juziType);
			criteria=criteria.andJuzileixingEqualTo(temp);
			currentPage=0;
		}
		if(chuchu!=null&&!"".equals(chuchu)){
			criteria=criteria.andJuzichuchuLike("%"+chuchu+"%");
			currentPage=0;
		}
		juziExample.getOredCriteria().add(criteria);
		Page2<Juzi, JuziExample> page2 = new Page2<Juzi, JuziExample>(juziExample, currentPage, size);
		page2=juziservice.findJuziByPage(page2);
		modelAndView.addObject("page2",page2);
		modelAndView.addObject("juzileixing",juZiTypeService.finAll());
		modelAndView.addObject("juzitype",juziType);
		modelAndView.addObject("chuchu",chuchu);
		modelAndView.addObject("juzi",juzi);
		modelAndView.setViewName("/Houtai/juzi");
		return modelAndView;
		
	}
}

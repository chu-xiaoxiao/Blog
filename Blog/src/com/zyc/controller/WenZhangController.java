package com.zyc.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.fabric.Response;
import com.sun.org.apache.xalan.internal.xsltc.trax.OutputSettings;
import com.sun.org.apache.xml.internal.serialize.Printer;
import com.zyc.model.Page;
import com.zyc.model.WenZhang;
import com.zyc.model.WenZhangSearch;
import com.zyc.service.WenZhangService;

import net.sf.json.JSONObject;

@Controller
public class WenZhangController {
	@Autowired
	private WenZhangService wenZhangService;
	@RequestMapping(value="/Houtai/addWenZhang.do")
	public ModelAndView addWenZhang(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		WenZhang wenZhang = new WenZhang();
		wenZhang.setWenzhangneirong(request.getParameter("wenzhangneirong"));
		wenZhang.setWenzhangbiaoti(request.getParameter("wenzhangbiaoti"));
		wenZhang.setWenzhangchunwenben(request.getParameter("wenzhangchunwenben").trim());
		wenZhang.setWenzhangleixing(request.getParameter("wenzhangleixing"));
		wenZhangService.addWenZhang(wenZhang);
		return new ModelAndView("redirect:/Houtai/findByPage.do");
	}
	@RequestMapping(value="/WenZhang/findAll")
	public String findAll(HttpServletRequest request){
		request.setAttribute("wenzhang", wenZhangService.findAllWenZhang());
		return "/Houtai/findByPage";
	}
	@RequestMapping(value = "/Houtai/delete")
	public ModelAndView deleteWenZhang(HttpServletRequest request){
		Integer id = Integer.parseInt(request.getParameter("wenzhangid"));
		wenZhangService.deleteWenZhang(id);
		
		return new ModelAndView("redirect:/Houtai/findByPage.do");
	}
	@RequestMapping(value="/Houtai/modifywenzhangaction")
	public ModelAndView modifyWenZhang(HttpServletRequest request,HttpServletResponse response){
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
		/*JSONObject jsonObject = new JSONObject();
		jsonObject.put("wenzhang", wenZhang);
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(response.getOutputStream());
			printWriter.println(wenZhang);
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	@RequestMapping("/Houtai/modifywenzhang")
	public void findWenZhangByid(HttpServletRequest request,PrintWriter printWriter){
		WenZhang wenZhang = wenZhangService.findWenZhangByid(Integer.parseInt(request.getParameter("wenzhangid")));
		JSONObject json = new JSONObject();
		json.put("wenzhang", wenZhang);
		printWriter.print(json.toString());
		printWriter.flush();
		printWriter.close();
	}
	@RequestMapping("/Houtai/findByPage.**")
	public ModelAndView finWenZhangByPage(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		WenZhangSearch wenZhangSearch = new WenZhangSearch();
		if(request.getParameter("wenzhangbiaoti")!=null&&!"".equals(request.getParameter("wenzhangbiaoti"))){
			wenZhangSearch.setName(request.getParameter("wenzhangbiaoti"));
		}else{
			wenZhangSearch.setName(null);
		}
		if(request.getParameter("wenzhangleixing")!=null&&!"".equals(request.getParameter("wenzhangleixing"))){
			wenZhangSearch.setType(request.getParameter("wenzhangleixing"));
		}else{
			wenZhangSearch.setType(null);
		}
		Page<WenZhang> page = new Page<WenZhang>();
		if(request.getParameter("currentPage")!=null){
			if(page.getAllPage()!=null){
				if(page.getAllPage()<Integer.parseInt(request.getParameter("currentPage"))){
					page.setCurrentPage(1);
				}else{
					page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
				}
			}else{
				page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
			}
		}else{
			page.setCurrentPage(1);
		}
		if(request.getParameter("sieze")!=null){
			page.setSieze(Integer.parseInt(request.getParameter("sieze")));
		}else{
			page.setSieze(10);
		}
		page.setWenZhangSearch(wenZhangSearch);
		if(page.getCurrentPage()!=0){
			page.setStatr((page.getCurrentPage()-1)*page.getSieze());
		}else{
			page.setStatr(0);
		}
		page.setEnd(page.getCurrentPage()*page.getSieze());
		page = wenZhangService.findWenZhangBySearch(page);
		modelAndView.addObject(page);
		modelAndView.setViewName("/Houtai/ListWenZhangByPage");
		/*try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("page", page);
			PrintWriter printWriter = new PrintWriter(response.getOutputStream());
			printWriter.println(page);
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return modelAndView;
	}
	@RequestMapping("/Houtai/findByPage2.**")
	public void finWenZhangByPage2(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		WenZhangSearch wenZhangSearch = new WenZhangSearch();
		if(request.getParameter("wenzhangbiaoti")!=null&&!"".equals(request.getParameter("wenzhangbiaoti"))){
			wenZhangSearch.setName(request.getParameter("wenzhangbiaoti"));
		}else{
			wenZhangSearch.setName(null);
		}
		if(request.getParameter("wenzhangleixing")!=null&&!"".equals(request.getParameter("wenzhangleixing"))){
			wenZhangSearch.setType(request.getParameter("wenzhangleixing"));
		}else{
			wenZhangSearch.setType(null);
		}
		Page<WenZhang> page = new Page<WenZhang>();
		if(request.getParameter("currentPage")!=null){
			if(page.getAllPage()!=null){
				if(page.getAllPage()<Integer.parseInt(request.getParameter("currentPage"))){
					page.setCurrentPage(1);
				}else{
					page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
				}
			}else{
				page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
			}
		}else{
			page.setCurrentPage(1);
		}
		if(request.getParameter("sieze")!=null){
			page.setSieze(Integer.parseInt(request.getParameter("sieze")));
		}else{
			page.setSieze(4);
		}
		page.setWenZhangSearch(wenZhangSearch);
		if(page.getCurrentPage()!=0){
			page.setStatr((page.getCurrentPage()-1)*page.getSieze());
		}else{
			page.setStatr(0);
		}
		page.setEnd(page.getCurrentPage()*page.getSieze());
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
		//return modelAndView;
	}
	@RequestMapping(value={"/wenzhang/index.**","/wenzhang/blogs.**"})
	public ModelAndView finWenZhangByPageIndex(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		WenZhangSearch wenZhangSearch = new WenZhangSearch();
		if(request.getParameter("wenzhangbiaoti")!=null&&!"".equals(request.getParameter("wenzhangbiaoti"))){
			wenZhangSearch.setName(request.getParameter("wenzhangbiaoti"));
		}else{
			wenZhangSearch.setName(null);
		}
		if(request.getParameter("wenzhangleixing")!=null&&!"".equals(request.getParameter("wenzhangleixing"))){
			wenZhangSearch.setType(request.getParameter("wenzhangleixing"));
		}else{
			wenZhangSearch.setType(null);
		}
		Page<WenZhang> page = new Page<WenZhang>();
		if(request.getParameter("currentPage")!=null){
			if(page.getAllPage()!=null){
				if(page.getAllPage()<Integer.parseInt(request.getParameter("currentPage"))){
					page.setCurrentPage(1);
				}else{
					page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
				}
			}else{
				page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
			}
		}else{
			page.setCurrentPage(1);
		}
		if(request.getParameter("sieze")!=null){
			page.setSieze(Integer.parseInt(request.getParameter("sieze")));
		}else{
			if(request.getRequestURL().toString().contains("index")){
				page.setSieze(4);
			}else{
				page.setSieze(12);
			}
		}
		page.setWenZhangSearch(wenZhangSearch);
		if(page.getCurrentPage()!=0){
			page.setStatr((page.getCurrentPage()-1)*page.getSieze());
		}else{
			page.setStatr(0);
		}
		page.setEnd(page.getCurrentPage()*page.getSieze());
		page = wenZhangService.findWenZhangBySearch(page);
		modelAndView.addObject(page);
		if(request.getRequestURL().toString().contains("index")){
			modelAndView.setViewName("/wenzhang/index");
		}
		if(request.getRequestURL().toString().contains("blog")){
			modelAndView.setViewName("/wenzhang/blog");
		}
		return modelAndView;
	}
	@RequestMapping("/wenzhang/xiangxi")
	public ModelAndView xiangxi(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if(request.getParameter("wenzhangid")!=null){
			WenZhang wenZhang = wenZhangService.findWenZhangByid(Integer.parseInt(request.getParameter("wenzhangid")));
			modelAndView.addObject(wenZhang);
		}
		modelAndView.setViewName("/wenzhang/about");
		return modelAndView;
	}
	
	@RequestMapping(value="/Houtai/upLoadImg")
	public void upload(@RequestParam(value="wangEditorH5File",required=false) MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
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
                        String fileName ="/"+UUID.randomUUID().toString().replace("-", "")+"."+file1.getOriginalFilename().split("\\.")[1];  
                        //定义上传路径   
                        //String path = request.getServletContext().getRealPath("imgs")+fileName;  
                        String hash = Calendar.getInstance().get(Calendar.YEAR)+"_"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"_"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                        String path ="/home/imgs/"+hash;
                        File pathf = new File(path);  
                        if(!pathf.exists()){
                        	pathf.mkdirs();
                        }
                        path = "/home/imgs/"+hash+fileName;
                        File localFile = new File(path);  
                        file1.transferTo(localFile); 
                        //output.print(request.getServletContext().getContextPath()+"/imgs/"+fileName);
                        output.print(path);
                    }  
                }  
            }  
              
        }
	}
	@RequestMapping(value="/wenzhang/randWenZhang")
	public ModelAndView randWenZhang(){
		ModelAndView modelAndView = new ModelAndView();
		List<WenZhang> wenZhangs = wenZhangService.randWenZhang(1);
		modelAndView.addObject(wenZhangs.get(0));
		modelAndView.setViewName("/wenzhang/randpage");
		return modelAndView;
	}
	@RequestMapping(value="/Houtai/index.do")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("count", wenZhangService.countWenzhang());
		modelAndView.setViewName("/Houtai/index1");
		return modelAndView;
	}
}

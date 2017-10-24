package com.zyc.controller;

import com.zyc.model.City;
import com.zyc.service.CityService;
import com.zyc.util.HttpclientUtil;
import com.zyc.util.MyException;
import com.zyc.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by YuChen Zhang on 17/10/10.
 */
@Controller
@RequestMapping("webutil")
public class testcontroller {
    @Autowired
    @Qualifier("cityServiceImplements")
    CityService cityService;
    /**
     * 以get方式请求当前url结果
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping("simulationGet.do")
    public void simulationGet(HttpServletResponse response, HttpServletRequest request) throws IOException {
        HttpclientUtil httpclientUtil = new HttpclientUtil();
        String result = null;
        JSONObject jsonObject = new JSONObject();
        if(StringUtil.judeStringIsNullAndVoid(request.getParameter("url"))) {
            result = httpclientUtil.getDocumentFromUriGet(request.getParameter("url"));
            jsonObject.put("result",result);
            //jsonObject.put("resultUTF", URLDecoder.decode(result,"UTF-8"));
        }else{
            jsonObject.put("errcode","-0001");
            jsonObject.put("errmsg","请求url不能为空");
            result = jsonObject.toString();
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonObject.toString());
        printWriter.close();
    }

    /**
     * 以post方式请求当前url结果
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping("simulationPost.do")
    public void simulationPost (HttpServletResponse response,HttpServletRequest request) throws IOException {
        HttpclientUtil httpclientUtil = new HttpclientUtil();
        String result = null;
        JSONObject jsonObject = new JSONObject();
        if(StringUtil.judeStringIsNullAndVoid(request.getParameter("url"))) {
            result = httpclientUtil.getDocumentFromUriPost(request.getParameter("url"));
        }else{
            jsonObject.put("errcode","-0001");
            jsonObject.put("errmsg","请求url不能为空");
            jsonObject.put("result",result);
            jsonObject.put("resultUTF", URLDecoder.decode(result,"UTF-8"));
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonObject.toString());
        printWriter.close();
    }

    /**
     * 根据前台城区获取下级城市信息
     * @param request
     * @param response
     */
    @RequestMapping("getNextLevelCity.do")
    public void getNextLevelCity(HttpServletRequest request,HttpServletResponse response) throws IOException {
        City city = null;
        PrintWriter out = response.getWriter();
        if(StringUtil.judeStringIsNullAndVoid(request.getParameter("cityId"))){
            //根据ip获取城市信息
           city = cityService.getCityByPrimaryKey(Integer.valueOf(request.getParameter("cityId")));
        }else{
            out.print("id不能为空");
            out.close();
            return;
        }
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            //根据城市信息获取下级城市信息
            for(City temp : cityService.getNextLevel(city)) {
                //循环放入json数组
                jsonArray.add(temp);
            }
        } catch (MyException e) {
            e.printStackTrace();
            jsonObject.put("error",e.getMessage());
        }
        jsonObject.put("citys",jsonArray);
        out.print(jsonObject);
        out.flush();;
        out.close();
    }
}

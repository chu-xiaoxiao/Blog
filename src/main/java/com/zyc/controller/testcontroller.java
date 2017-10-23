package com.zyc.controller;

import com.zyc.util.HttpclientUtil;
import com.zyc.util.StringUtil;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
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
}

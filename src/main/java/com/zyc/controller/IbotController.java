package com.zyc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONObject;

@Controller("ibotController")
@RequestMapping("/Ibot")
public class IbotController {
	
	@RequestMapping(value= "/getmsg.do")
	public void getmsg(HttpServletRequest request,HttpServletResponse response,@CookieValue("JSESSIONID")String sessionid) throws ClientProtocolException, IOException, ParseException{
		String problem = request.getParameter("xiaoxi");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://www.tuling123.com/openapi/api");
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("key","3d0c4439dd3141bf9e657b68ef8bc4d4");
		jsonObject.put("info", problem);
		jsonObject.put("userid", sessionid);
		
		StringEntity stringEntity = new StringEntity(jsonObject.toString(),"UTF-8");
		stringEntity.setContentEncoding("UTF-8");
		stringEntity.setContentType("application/json");
		httpPost.setEntity(stringEntity);
		HttpResponse httprespohn = httpClient.execute(httpPost);
		HttpEntity entity = httprespohn.getEntity();
		String result = EntityUtils.toString(entity,"UTF-8");
		
		JSONObject jsonObject2 = jsonObject.fromObject(result);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		jsonObject2.put("time",simpleDateFormat.format(new Date()));
		
		PrintWriter printWriter = new PrintWriter(response.getOutputStream());
		printWriter.print(jsonObject2.toString());
		printWriter.flush();
		printWriter.close();
	}
	@RequestMapping("/gettime.do")
	public void gettime(){

	}
}

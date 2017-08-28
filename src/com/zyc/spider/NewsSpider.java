package com.zyc.spider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

public class NewsSpider {
	public JSONObject sina() throws ClientProtocolException, IOException, ParseException, JSONException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String date = simpleDateFormat.format(calendar.getTime().getTime());
		HttpGet httpGet = new HttpGet("http://top.news.sina.com.cn/ws/GetTopDataList.php?top_type=day&top_cat=news_world_suda&top_time="+date+"&top_show_num=20&top_order=DESC&js_var=news_");
		httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity,"UTF-8");
		result = result.split("=")[1];
		JSONObject jsonObject = new JSONObject(result.substring(0,result.length()-1));
		return jsonObject;
	}
	
	public Map<String, String> getNews() throws ClientProtocolException, ParseException, JSONException, IOException{
		Map<String, String> result = new HashMap<String,String>();
		JSONArray jsonArray = this.sina().getJSONArray("data");
		for(int i=0;i<10;i++){
			JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
			result.put(jsonObject.getString("title"), jsonObject.getString("url"));
		}
		return result;
	}
}

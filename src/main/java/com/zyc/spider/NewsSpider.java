package com.zyc.spider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.xml.crypto.Data;
public class NewsSpider {
    static{
        try {
            SpiderUtil.flushDateAndData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Jedis jedis = com.zyc.util.JedisPool.getJedis();
    Logger logger = LogManager.getLogger(NewsSpider.class);
    /**
     * 新浪新闻api爬取
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @throws ParseException
     * @throws JSONException
     */
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
		JSONObject jsonObject =JSONObject.fromObject(result.substring(0,result.length()-2));
        logger.info("从api接口获取新闻信息");
		return jsonObject;
	}

    /**
     * 从新浪新闻api获取新闻信息,并存入redis
     * @return
     * @throws ClientProtocolException
     * @throws ParseException
     * @throws JSONException
     * @throws IOException
     */
	public Map<String, String> getNewsFromSinaToRedis() throws ClientProtocolException, ParseException, JSONException, IOException{
		Map<String, String> result = new HashMap<String,String>();
		JSONArray jsonArray = this.sina().getJSONArray("data");
        SpiderUtil.flushDate();
        //清空当前Redis中的新闻纪录
		jedis.del("newsFromSina");
		for(int i=0;i<10;i++){
            JSONObject jsonObject =JSONObject.fromObject(jsonArray.get(i).toString());
            result.put(jsonObject.getString("title"), jsonObject.getString("url"));
            jedis.lpush("newsFromSina",jsonObject.toString());
        }
		return result;
	}

    /**
     * 获取新闻消息，在时间戳与系统时间一致的时从Redis中获取新闻信息
     * 当时间戳与系统时间不一致时从sina接口获取消息
     * @return
     * @throws IOException
     */
    public Map<String,String> getNews() throws IOException {
	    Map<String,String> result = new HashMap<String,String>();
        Date date = new Date();
        //当日期不同时刷新新闻
	    if(!SpiderUtil.validateDate()) {
            SpiderUtil.flushDateAndData();
        }
        List<String> getFromRedis = jedis.lrange("newsFromSina",0,-1);
        for(String temp:getFromRedis){
            JSONObject jsonObject = JSONObject.fromObject(temp);
            result.put(jsonObject.getString("title"),jsonObject.getString("url"));
        }
        return result;
    }
}


package com.zyc.spider;

import com.zyc.model.NewsType;
import com.zyc.util.HttpclientUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class NewsSpider {
    private Jedis jedis = com.zyc.util.JedisPool.getJedis();
    Logger logger = LogManager.getLogger(NewsSpider.class);

    /**
     * 新浪新闻api爬取
     *
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @throws ParseException
     * @throws JSONException
     */
    public JSONObject sina(String url) throws ClientProtocolException, IOException, ParseException, JSONException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(calendar.getTime().getTime());
        HttpclientUtil httpclientUtil = new HttpclientUtil();
        String result = httpclientUtil.getDocumentFromUriGet(url);
        result = result.split("= \\{")[1];
        result = "{" + result.split("if \\(")[0];
        result = result.substring(0, result.length() - 2);
        logger.debug(result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        logger.info("从" + url + "获取新闻信息");
        return jsonObject;
    }

    /**
     * 从新浪新闻api获取新闻信息,并存入redis
     *
     * @return
     * @throws ClientProtocolException
     * @throws ParseException
     * @throws JSONException
     * @throws IOException
     */
    public void getNewsFromSinaToRedis() throws ClientProtocolException, ParseException, JSONException, IOException {
        Map<String, String> result = new HashMap<String, String>();
        //读取新闻url配置文件
        Properties properties = new Properties();
        properties.load(NewsSpider.class.getClassLoader().getResourceAsStream("News.properties"));
        for (Object temp : properties.keySet()) {
            String url = properties.getProperty((String) temp);
            url = this.replaceDateAndNum(url, new Date(), 10);
            JSONArray jsonArray = this.sina(url).getJSONArray("data");
            //清空当前Redis中的新闻纪录
            jedis.del((String) temp);
            for (int i = 0; i < 10; i++) {
                JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i).toString());
                jedis.lpush((String) temp, jsonObject.toString());
                logger.info(jsonObject.toString());
            }
        }
    }

    /**
     * 获取新闻消息，在时间戳与系统时间一致的时从Redis中获取新闻信息
     * 当时间戳与系统时间不一致时从sina接口获取消息
     *
     * @return
     * @throws IOException
     */
    public List<String> getNews(NewsType newsType) throws IOException {
        List result = new ArrayList<String>();
/*        //当日期不同时刷新新闻
        if(!SpiderUtil.validateDate()) {
            SpiderUtil.flushDateAndData();
        }*/
        List<String> getFromRedis = jedis.lrange(newsType.getType(), 0, -1);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for (String temp : getFromRedis) {
            jsonArray.add(temp);
        }
        jsonObject.put("date", jsonArray);
        result.add(jsonObject.toString());
        return result;
    }

    /**
     * 将传过来的值替换配置文件里面{}中的值
     *
     * @param old
     * @param date
     * @param num
     * @return
     */
    public String replaceDateAndNum(String old, Date date, Integer num) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String sDate = simpleDateFormat.format(date);
        old = old.replace("<date>", sDate);
        old = old.replace("<num>", num.toString());
        return old;
    }
}


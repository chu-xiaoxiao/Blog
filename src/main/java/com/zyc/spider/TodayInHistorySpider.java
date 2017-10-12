package com.zyc.spider;

import com.zyc.util.HttpclientUtil;
import com.zyc.util.JedisPoolUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by YuChen Zhang on 17/09/13.
 */
public class TodayInHistorySpider {
    private String getDocumentFrom;
    private Logger logger = LogManager.getLogger(TodayInHistorySpider.class);
    private static List<Map.Entry<String,String>> todayInHistorySpider;
    /**
     * 从uri中获取历史上今天的数据
     * @throws IOException
     */
    public List<Map.Entry<String,String>> getHistoryFromURL() throws IOException{
        Map<String,String> history = new LinkedHashMap<>();
        HttpclientUtil httpclientUtil = new HttpclientUtil();
        String result = httpclientUtil.getDocumentFromUriGet("http://www.todayonhistory.com/");
        Document document = Jsoup.parse(result);
        Elements elements =  document.getElementsByClass("t");
        for(Element element:elements){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            Date date = null;
            try {
                date = simpleDateFormat.parse(element.getElementsByTag("span").text());
            } catch (ParseException e) {
            }
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            history.put(element.getElementsByClass("txt").attr("href"),element.getElementsByClass("txt").text()+"::"+simpleDateFormat.format(date));
        }
        //将map.entry转换为list
        List<Map.Entry<String,String>> list = new ArrayList<>(history.entrySet());
        //重写比较器
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map .Entry<String, String> o1, Map.Entry<String, String> o2) {
                Integer t1 = Integer.parseInt(o1.getValue().split("::")[1].split("-")[0]);
                Integer t2 = Integer.parseInt(o2.getValue().split("::")[1].split("-")[0]);
                if(t1>t2){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        logger.info("从URI上获取历史上的今天");
        this.todayInHistorySpider=list;
        return list;
    }

    /**
     * 将抽取的数据放到Redis中
     * @throws IOException
     */
    public void setHistroyToRedis() throws IOException {
        Jedis jedis = JedisPoolUtil.getJedis();
        List<String> dateAndText = new ArrayList<String>();
        //将返回的map组合为list集合
        jedis.del("TodayInHistory");
        for(Map.Entry<String,String> temp :this.getHistoryFromURL()){
            jedis.lpush("TodayInHistory",temp.getKey()+"__"+temp.getValue());
        }
        JedisPoolUtil.returnRes(jedis);
    }

    /**
     * 取得历史上今天的数据
     * @return
     * @throws IOException
     */
    public Map<String,String> getTodayInHistory() throws IOException {
        Jedis jedis = JedisPoolUtil.getJedis();
        Map<String,String> result = new HashMap<String,String>();
        //从redis中取出所有的历史上的今天的数据封装入map
        List<String> tempResult = jedis.lrange("TodayInHistory",0,-1);
        for(String temp :jedis.lrange("TodayInHistory",0,-1)){
            String[] temp1 = temp.split("__");
            result.put(temp1[0],temp1[1]);
        }
        JedisPoolUtil.returnRes(jedis);
        return result;
    }

    public List<Map.Entry<String, String>> getTodayInHistorySpider() {
        return todayInHistorySpider;
    }

    public void setTodayInHistorySpider(List<Map.Entry<String, String>> todayInHistorySpider) {
        this.todayInHistorySpider = todayInHistorySpider;
    }
}

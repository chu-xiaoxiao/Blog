package com.zyc.util;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 获取访问ip工具类
 */
public class IPUtils {
	public static String getIpaddr(HttpServletRequest request){
		if(request.getHeader("x-forwarded-for")==null){
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	/**
	 * 淘宝api获取访问ip
	 *
	 * @param ip
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException
	 */
	public static String getLocatsion(String ip) throws ClientProtocolException, IOException, JSONException{
	    //过滤本地测试ip
		if(ip.contains("0:0:0:0:0:0:0:1")||ip.contains("127")){
			return null;
		}
		StringBuffer get = new StringBuffer();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
		httpGet.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
		HttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity,"UTF-8");
		System.out.println(result);
		JSONObject jsonObject = JSONObject.fromObject(result);
		jsonObject = jsonObject.getJSONObject("data");
		get.append(URLDecoder.decode(jsonObject.get("area").toString(),"UTF-8"));
		get.append(",");
		get.append(URLDecoder.decode(jsonObject.get("region").toString(),"UTF-8"));
		get.append(",");
		get.append(URLDecoder.decode(jsonObject.get("city").toString(),"UTF-8"));
		get.append(",");
		get.append(URLDecoder.decode(jsonObject.get("isp").toString(),"UTF-8"));
		return get.toString();
	}
	@Test
	public void testGet(){
		try {
			System.out.println(IPUtils.getLocatsion("123.206.8.180"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    /**
     * 通过高德地图获取ip地理位置以及坐标
     * @param ip
     * @return
     * @throws IOException
     */
	public static JSONObject getLocationByGode(String ip) throws IOException {
		if(IPUtils.isLoopbackaddress(ip)){
			return null;
		}
        HttpclientUtil httpclientUtil = new HttpclientUtil();
        String result = httpclientUtil.getDocumentFromUriGet("http://restapi.amap.com/v3/ip?key=5e6c1c249358728248c5acef46ba179c&ip="+ip);
        JSONObject jsonObject = new JSONObject();
        jsonObject = JSONObject.fromObject(result);
        return jsonObject;
    }

	/**
	 * 判断是否为本机回环地址
	 * @param ip
	 * @return
	 */
    public static boolean isLoopbackaddress(String ip){
		if(ip.contains("0:0:0:0:0:0:0:1")||ip.contains("127")){
			return true;
		}else{
			return false;
		}
	}
}

package com.zyc.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * httpclient 工具类
 * Created by YuChen Zhang on 17/09/13.
 */
public class HttpclientUtil {
    private CloseableHttpClient httpClient = HttpClients.createDefault();

    public String getDocumentFromUriPost(String uri) throws IOException {
        if(!uri.contains("http:\\\\")) {
            uri = "http:\\\\"+uri;
        }
        //设置HTTP头
        HttpPost post = new HttpPost(uri);
        post.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        post.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        post.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        CloseableHttpResponse response = httpClient.execute(post);
        HttpEntity httpEntity = response.getEntity();
        return EntityUtils.toString(httpEntity,"UTF-8");
    }
    public String getDocumentFromUriGet(String uri) throws IOException {
        if(!uri.contains("http:\\\\")&&!uri.contains("http://")) {
            uri = "http://"+uri;
        }
        //设置HTTP头
        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        get.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        get.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        CloseableHttpResponse response = httpClient.execute(get);
        HttpEntity httpEntity = response.getEntity();
        return EntityUtils.toString(httpEntity,"UTF-8");
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }
}

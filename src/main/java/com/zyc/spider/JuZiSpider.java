package com.zyc.spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zyc.mapper.JuziMapper;
import com.zyc.mapper.JuziTypeMapper;
import com.zyc.model.Juzi;
import com.zyc.model.JuziExample;
import com.zyc.model.JuziTypeKey;
import com.zyc.model.Page2;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
@Component("juZiSpider")
public class JuZiSpider implements JuziService,PageProcessor{

    Logger logger = LogManager.getLogger(JuZiSpider.class);

	private Site site = Site.me()
			.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
			.addHeader("Accept-Encoding", "gzip, deflate, sdch")
			.addHeader("Accept-Language", "zh-CN,zh;q=0.8")
			.addHeader("Connection", "keep-alive")
			.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
			.setRetryTimes(5).setSleepTime(5000);
	@Autowired
	@Qualifier("juZiPipeline")
	private Pipeline Pipeline;
	
	@Autowired
	@Qualifier("juziMapper")
	JuziMapper juziMapper;
	
	@Autowired
	@Qualifier("juziTypeMapper")
	JuziTypeMapper juziTypeMapper;
	
	public void updateJuzi(String url){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpGet = new HttpPost("http://www.juzimi.com/");
		httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
		HttpResponse response;
		try {
			response = httpClient.execute(httpGet);
			Header[] headers = response.getAllHeaders();
			for(Header temp: headers){
				this.getSite().addCookie(temp.getName(), temp.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Spider.create(new JuZiSpider()).addUrl(url).addPipeline(Pipeline).thread(5).run();
	}
	@Override
	public Site getSite() {
		return this.site;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void process(Page page) {
		Juzi juzi =null;
		List<Juzi> juzis = new ArrayList<Juzi>();
		List<String> urls = page.getHtml().css("div.item-list").links().regex("/tags/.*").all();
		for(String temp:urls){
			page.addTargetRequest("http://www.juzimi.com"+temp);
		}
		logger.debug("Download from"+page.getUrl());
		List<String> juziget = page.getHtml().css("div.views-field-phpcode").all();
		//获取页面中有关类别的节点 例如本站所收录关于"成长"的句子
	    String type = Jsoup.parse(page.getHtml().css("div.xqfamwritercount").get()).getElementsByClass("xqfamwritercount").text();
	    //截取字符串
	    type=type.split("\"")[1];
	    JuziTypeKey juziType = new JuziTypeKey();
	    juziType.setLeixingming(type);
		for(int i=0;i<juziget.size();i++){
			Document document = Jsoup.parse(juziget.get(i));
			juzi = new Juzi();
			juzi.setJuzineirong(document.getElementsByClass("xlistju").text());
			String string = document.getElementsByClass("views-field-field-oriwriter-value").text();
			if(string==null||"".equals(string.trim())){
				juzi.setJuzichuchu("无");
			}else{
				juzi.setJuzichuchu(string);
			}
			juzi.setJuzileixing(juziType.getLeixingid());
			juzi.setTianjiashijian(new Date());
			juzis.add(juzi);
			logger.info(juzi.toString());
		}
		page.putField("juziResult", juzis);
		page.putField("juziType", juziType);
	}
	@Test
	public void testGet(){
		Spider.create(new JuZiSpider()).addUrl("http://www.juzimi.com/tags/%E5%94%AF%E7%BE%8E").addPipeline(Pipeline).thread(5).run();
	}
	@Override
	public Page2<Juzi, JuziExample> findJuziByPage(Page2<Juzi, JuziExample> page2) {
		page2.setAllPage(page2.countAllPage(juziMapper.countByExample(page2.getE())));
		page2.getE().setLimit(page2.getSize());
		page2.getE().setOffset(page2.getStart());
		page2.setLists(juziMapper.selectByExample(page2.getE()));
		page2.getE().setOrderByClause("desc tianjiariqi");
		return page2;
	}
	@Override
	public Integer countJuZiByExample(JuziExample juziExample) {
		return juziMapper.countByExample(juziExample);
	}
}

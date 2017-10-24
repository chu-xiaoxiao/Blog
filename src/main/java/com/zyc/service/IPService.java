package com.zyc.service;

import java.io.IOException;
import java.util.List;

import com.zyc.model.Ip;
import com.zyc.model.IpExample;
import org.apache.http.client.ClientProtocolException;

import com.zyc.model.Ip_Date;
import com.zyc.model.Page2;

import net.sf.json.JSONException;

public interface IPService {
	void addIP(Ip ip) throws JSONException, ClientProtocolException, IOException;
	Page2<Ip, IpExample> findIpByPage(Page2<Ip, IpExample> page2);
	Long countIP(IpExample ipExample);
	List<Ip_Date> selectCountByDay(Integer count);
	void updateIPByKey(Ip ip);
}

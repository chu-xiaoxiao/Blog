package com.zyc.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.zyc.model.IP;
import com.zyc.model.IPExample;
import com.zyc.model.Ip_Date;
import com.zyc.model.Page2;

import net.sf.json.JSONException;

public interface IPService {
	void addIP(IP ip) throws JSONException, ClientProtocolException, IOException;
	Page2<IP, IPExample> findIpByPage(Page2<IP, IPExample> page2);
	Integer countIP(IPExample ipExample);
	List<Ip_Date> selectCountByDay(Integer count);
}

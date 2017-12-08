package com.zyc.service;

import com.zyc.model.*;
import net.sf.json.JSONException;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.util.List;

public interface IPService {
	void addIP(Ip ip) throws JSONException, ClientProtocolException, IOException;
	Page2<Ip, IpExample> findIpByPage(Page2<Ip, IpExample> page2);
	Long countIP(IpExample ipExample);
	List<Ip_Date> selectCountByDay(Integer count);
	void updateIPByKey(Ip ip);
	List<GroupCount> selectCountByContry();
}

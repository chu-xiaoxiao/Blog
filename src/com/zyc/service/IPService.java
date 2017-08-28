package com.zyc.service;

import java.util.List;

import com.zyc.model.IP;
import com.zyc.model.IPExample;
import com.zyc.model.Ip_Date;
import com.zyc.model.Page2;

public interface IPService {
	void addIP(IP ip);
	Page2<IP, IPExample> findIpByPage(Page2<IP, IPExample> page2);
	Integer countIP(IPExample ipExample);
	List<Ip_Date> selectCountByDay(Integer count);
}

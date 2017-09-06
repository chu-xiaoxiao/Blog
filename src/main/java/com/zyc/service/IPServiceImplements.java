package com.zyc.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zyc.mapper.IPMapper;
import com.zyc.model.IP;
import com.zyc.model.IPExample;
import com.zyc.model.Ip_Date;
import com.zyc.model.Page2;
import com.zyc.util.IPUtils;

import net.sf.json.JSONException;

@Service("iPServiceImplements")
@Transactional
public class IPServiceImplements implements IPService {
	@Autowired
	IPMapper iPMapper;

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void addIP(IP ip) throws JSONException, ClientProtocolException, IOException {
		ip.setLocation(IPUtils.getLocatsion(ip.getIp()));
		iPMapper.insert(ip);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Page2<IP, IPExample> findIpByPage(Page2<IP, IPExample> page2) {

		page2.setAllPage(page2.countAllPage(iPMapper.countByExample(page2.getE())));
		page2.getE().setLimit(page2.getSize());
		page2.getE().setOffset(page2.getStart());
		page2.getE().setOrderByClause("date desc");
		page2.setLists(iPMapper.selectByExample(page2.getE()));
		return page2;
	}

	@Override
	public Integer countIP(IPExample ipExample) {
		return iPMapper.countByExample(ipExample);
	}

	@Override
	public List<Ip_Date> selectCountByDay(Integer count) {
		return iPMapper.selectCountByDay(count);
	}

}

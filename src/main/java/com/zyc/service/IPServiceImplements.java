package com.zyc.service;

import java.io.IOException;
import java.util.List;

import com.zyc.mapper.IpMapper;
import com.zyc.model.Ip;
import com.zyc.model.IpExample;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zyc.model.Ip_Date;
import com.zyc.model.Page2;
import com.zyc.util.IPUtils;

import net.sf.json.JSONException;

@Service("iPServiceImplements")
@Transactional
public class IPServiceImplements implements IPService {
	@Autowired
	IpMapper iPMapper;

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void addIP(Ip ip) throws JSONException, ClientProtocolException, IOException {
		//ip.setLocation(IPUtils.getLocatsion(ip.getIp()));
		iPMapper.insert(ip);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Page2<Ip, IpExample> findIpByPage(Page2<Ip, IpExample> page2) {

		page2.setAllPage(page2.countAllPage(countIP(page2.getE()).intValue()));
		page2.getE().setLimit(page2.getSize());
		page2.getE().setOffset(page2.getStart());
		page2.getE().setOrderByClause("date desc");
		page2.setLists(iPMapper.selectByExample(page2.getE()));
		return page2;
	}

	@Override
	public Long countIP(IpExample ipExample) {
		return iPMapper.countByExample(ipExample);
	}

	@Override
	public List<Ip_Date> selectCountByDay(Integer count) {
		return iPMapper.selectCountByDay(count);
	}

    @Override
    public void updateIPByKey(Ip ip) {
        iPMapper.updateByPrimaryKeySelective(ip);
    }

}

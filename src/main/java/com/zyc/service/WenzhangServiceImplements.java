package com.zyc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import com.zyc.mapper.WenzhangMapper;
import com.zyc.model.Page;
import com.zyc.model.Page2;
import com.zyc.model.Wenzhang;
import com.zyc.model.WenzhangExample;
import com.zyc.util.CRDU;
import com.zyc.util.LogAop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("wenzhangServiceImplements")
@Transactional
public class WenzhangServiceImplements implements WenzhangService{
	@Resource
	WenzhangMapper wenzhangMapper;
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	public int addWenzhang(Wenzhang Wenzhang) {
		return wenzhangMapper.insertSelective(Wenzhang);
	}
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@LogAop(tableName = "wenzhang",CRDU = CRDU.Update,logRecord = "对文章内容进行更新")
	public int modifyWenzhang(Wenzhang Wenzhang) {
		return wenzhangMapper.updateByPrimaryKeySelective(Wenzhang);
	}
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public List<Wenzhang> findAllWenzhang() {
		return wenzhangMapper.selectByExample(new WenzhangExample());
	}
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	public void deleteWenzhang(Integer id) {
        wenzhangMapper.deleteByPrimaryKey(id);
	}
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public Wenzhang findWenzhangByid(Integer id) {
		return wenzhangMapper.selectByPrimaryKey(id);
	}
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)

	public Page2<Wenzhang,WenzhangExample> findWenzhangBySearch(Page2<Wenzhang,WenzhangExample> page2) {
        page2.setAllPage(page2.countAllPage(Math.toIntExact(wenzhangMapper.countByExample(page2.getE()))));
        page2.getE().setLimit(page2.getSize());
        page2.getE().setOffset(page2.getStart());
        page2.getE().setOrderByClause("wenzhangriqi desc");
        page2.setLists(wenzhangMapper.selectByExampleLeftSub(page2.getE()));
		return page2;
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public Integer countWenzhang() {
		return Math.toIntExact(wenzhangMapper.countByExample(new WenzhangExample()));
	}
}	

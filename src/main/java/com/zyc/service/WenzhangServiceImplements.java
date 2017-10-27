package com.zyc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zyc.mapper.WenzhangMapper;
import com.zyc.model.*;
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

	private HttpServletRequest request;

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	public int addWenzhang(Wenzhang wenzhang, User user) {
	    wenzhang.setWenzhangauthor(user.getId());
		return wenzhangMapper.insertSelective(wenzhang);
	}
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@LogAop(tableName = "wenzhang",CRDU = CRDU.Update,logRecord = "对文章内容进行更新")
	public int modifyWenzhang(Wenzhang Wenzhang,User user) {
		return wenzhangMapper.updateByPrimaryKeySelective(Wenzhang);
	}
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public List<Wenzhang> findAllWenzhang() {
		return wenzhangMapper.selectByExample(new WenzhangExample());
	}
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	public void deleteWenzhang(Integer id,User user) {
        wenzhangMapper.deleteByPrimaryKey(id);
	}
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public Wenzhang findWenzhangByid(Integer id,User user) {
		return wenzhangMapper.selectByPrimaryKey(id);
	}
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public Page2<Wenzhang,WenzhangExample> findWenzhangBySearch(Page2<Wenzhang,WenzhangExample> page2,User user) {
	    if(user!=null){
            page2.getE().getOredCriteria().add(page2.getE().createCriteria().andWenzhangauthorEqualTo(user.getId()));
        }
        page2.setAllPage(page2.countAllPage(Math.toIntExact(wenzhangMapper.countByExample(page2.getE()))));
        page2.getE().setLimit(page2.getSize());
        page2.getE().setOffset(page2.getStart());
        page2.getE().setOrderByClause("wenzhangriqi desc");
        page2.setLists(wenzhangMapper.selectByExampleLeftSub(page2.getE()));
		return page2;
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public Integer countWenzhang(User user) {
	    WenzhangExample wenzhangExample = new WenzhangExample();
	    wenzhangExample.getOredCriteria().add(wenzhangExample.createCriteria().andWenzhangauthorEqualTo(user.getId()));
		return Math.toIntExact(wenzhangMapper.countByExample(wenzhangExample));
	}
}	

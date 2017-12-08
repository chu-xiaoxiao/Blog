package com.zyc.service;

import com.zyc.aop.CRDU;
import com.zyc.aop.LogAop;
import com.zyc.mapper.WenzhangMapper;
import com.zyc.model.Page2;
import com.zyc.model.User;
import com.zyc.model.Wenzhang;
import com.zyc.model.WenzhangExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("wenzhangServiceImplements")
@Transactional
public class WenzhangServiceImplements implements WenzhangService {
	@Resource
	WenzhangMapper wenzhangMapper;

	private HttpServletRequest request;

	@Override
	@Transactional(rollbackFor=Exception.class,propagation= Propagation.REQUIRED)
	@LogAop(tableName = "wenzhang",CRDU = CRDU.Insert,logRecord = "添加文章")
	public int addWenzhang(Wenzhang wenzhang, User user) {
	    wenzhang.setWenzhangauthor(user.getId());
		return wenzhangMapper.insertSelective(wenzhang);
	}
	@Override
	@Transactional(rollbackFor=Exception.class,propagation= Propagation.REQUIRED)
	@LogAop(tableName = "wenzhang",CRDU = CRDU.Update,logRecord = "对文章内容进行更新")
	public int modifyWenzhang(Wenzhang Wenzhang,User user) {
		return wenzhangMapper.updateByPrimaryKeySelective(Wenzhang);
	}
	@Override
	@Transactional(readOnly=true,propagation= Propagation.REQUIRED)
	public List<Wenzhang> findAllWenzhang() {
		return wenzhangMapper.selectByExampleLeftSub(new WenzhangExample());
	}
	@Override
	@Transactional(rollbackFor=Exception.class,propagation= Propagation.REQUIRED)
    @LogAop(tableName = "wenzhang",CRDU = CRDU.Delete,logRecord = "删除文章")
	public void deleteWenzhang(Integer id,User user) {
        wenzhangMapper.deleteByPrimaryKey(id);
	}
	@Override
	@Transactional(readOnly=true,propagation= Propagation.REQUIRED)
	public Wenzhang findWenzhangByid(Integer id,User user) {
		return wenzhangMapper.selectByPrimaryKey(id);
	}
	@Override
	@Transactional(readOnly=true,propagation= Propagation.REQUIRED)
	public Page2<Wenzhang,WenzhangExample> findWenzhangBySearch(Page2<Wenzhang,WenzhangExample> page2, User user) {
	    if(user!=null){
            page2.getE().getOredCriteria().get(0).andWenzhangauthorEqualTo(user.getId());
        }else{
			page2.getE().getOredCriteria().get(0).andWenzhangauthorEqualTo(21);
		}
        page2.setAllPage(page2.countAllPage(Math.toIntExact(wenzhangMapper.countByExample(page2.getE()))));
        page2.getE().setLimit(page2.getSize());
        page2.getE().setOffset(page2.getStart());
        page2.getE().setOrderByClause("wenzhangriqi desc");
        page2.setLists(wenzhangMapper.selectByExampleLeftSub(page2.getE()));
		return page2;
	}

	@Override
	@Transactional(readOnly=true,propagation= Propagation.REQUIRED)
	public Integer countWenzhang(User user) {
	    WenzhangExample wenzhangExample = new WenzhangExample();
	    wenzhangExample.createCriteria().andWenzhangauthorEqualTo(user.getId());
		return Math.toIntExact(wenzhangMapper.countByExample(wenzhangExample));
	}
}	

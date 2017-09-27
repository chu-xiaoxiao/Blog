package com.zyc.service;

import java.util.List;

import com.zyc.model.Page;
import com.zyc.model.Page2;
import com.zyc.model.Wenzhang;
import com.zyc.model.WenzhangExample;

public interface WenzhangService {
	public int addWenzhang(Wenzhang Wenzhang);
	public int modifyWenzhang(Wenzhang Wenzhang);
	public List<Wenzhang> findAllWenzhang();
	public void deleteWenzhang(Integer id);
	public Wenzhang findWenzhangByid(Integer id);
	public Page2<Wenzhang,WenzhangExample> findWenzhangBySearch(Page2<Wenzhang,WenzhangExample> page);
	/**
	 * 获取所有文章数
	 * @return
	 */
	public Integer countWenzhang();
}

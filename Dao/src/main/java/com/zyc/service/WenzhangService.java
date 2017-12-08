package com.zyc.service;

import com.zyc.model.Page2;
import com.zyc.model.User;
import com.zyc.model.Wenzhang;
import com.zyc.model.WenzhangExample;

import java.util.List;

public interface WenzhangService {
	public int addWenzhang(Wenzhang Wenzhang, User user);
	public int modifyWenzhang(Wenzhang Wenzhang, User user);
	public List<Wenzhang> findAllWenzhang();
	public void deleteWenzhang(Integer id, User user);
	public Wenzhang findWenzhangByid(Integer id, User user);
	public Page2<Wenzhang,WenzhangExample> findWenzhangBySearch(Page2<Wenzhang, WenzhangExample> page, User user);
	/**
	 * 获取所有文章数
	 * @return
	 */
	public Integer countWenzhang(User user);
}

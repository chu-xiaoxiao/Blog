package com.zyc.service;

import java.util.List;

import com.zyc.model.*;

public interface WenzhangService {
	public int addWenzhang(Wenzhang Wenzhang, User user);
	public int modifyWenzhang(Wenzhang Wenzhang,User user);
	public List<Wenzhang> findAllWenzhang();
	public void deleteWenzhang(Integer id,User user);
	public Wenzhang findWenzhangByid(Integer id,User user);
	public Page2<Wenzhang,WenzhangExample> findWenzhangBySearch(Page2<Wenzhang,WenzhangExample> page,User user);
	/**
	 * 获取所有文章数
	 * @return
	 */
	public Integer countWenzhang(User user);
}

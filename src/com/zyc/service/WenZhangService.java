package com.zyc.service;

import java.util.List;

import com.zyc.model.Page;
import com.zyc.model.WenZhang;
import com.zyc.model.WenZhangSearch;

public interface WenZhangService {
	public int addWenZhang(WenZhang wenZhang);
	public int modifyWenZhang(WenZhang wenZhang);
	public List<WenZhang> findAllWenZhang();
	public void deleteWenZhang(Integer id);
	public WenZhang findWenZhangByid(Integer id);
	public Page<WenZhang> findWenZhangBySearch(Page<WenZhang> page);
	public List<WenZhang> findWenZhangBySearch(WenZhangSearch wenZhangSearch);
	/**
	 * 随机获取文章
	 * @param randSize
	 * @return
	 */
	public List<WenZhang> randWenZhang(Integer randSize);
	/**
	 * 获取所有文章数
	 * @return
	 */
	public Integer countWenzhang();
}

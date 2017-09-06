package com.zyc.mapper;

import java.util.List;

import com.zyc.model.Page;
import com.zyc.model.WenZhang;
import com.zyc.model.WenZhangSearch;

public interface WenZhangMapper {
	//ok
	/**
	 * 添加文章
	 * @param wenZhang
	 * @return
	 */
	Integer addWenZhang(WenZhang wenZhang);
	//ok
	/**
	 * 删除文章
	 * @param id
	 */
	void deleteWenzhang(Integer id);
	//ok
	/**
	 * 修改文章
	 * @param wenZhang
	 * @return
	 */
	Integer modifyWenZhang(WenZhang wenZhang);
	//ok
	/**
	 * 文章条件查询
	 * @param wenZhangSearch
	 * @return
	 */
	List<WenZhang> findAllWenZhang(WenZhangSearch wenZhangSearch);
	//ok
	/**
	 * 文章分页条件查询
	 * @param page
	 * @return
	 */
	List<WenZhang> findWenZhangByPage(Page<WenZhang>page);
	//ok
	/**
	 * 通过id查找文章
	 * @param id
	 * @return
	 */
	WenZhang findWenZhangById(Integer id);
	/**
	 * 随机取n条记录
	 * @param integer
	 * @return
	 */
	List<WenZhang> findWenZhangRand(Integer rAndSize);
	/**
	 * 查询文章总数
	 * @return
	 */
	Integer countWenzhang();
}

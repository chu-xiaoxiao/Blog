package com.zyc.mapper;

import java.util.List;

import com.zyc.model.JuZi;
import com.zyc.model.JuZiSearch;
import com.zyc.model.Page;

public interface JuziMapper {
	//ok
	/**
	 * 添加句子
	 * @param JuZi
	 * @return
	 */
	Integer addJuZi(JuZi JuZi);
	//ok
	/**
	 * 删除句子
	 * @param id
	 */
	void deleteJuZi(Integer id);
	//ok
	/**
	 * 修改句子
	 * @param JuZi
	 * @return
	 */
	Integer modifyJuZi(JuZi JuZi);
	//ok
	/**
	 * 句子条件查询
	 * @param JuZiSearch
	 * @return
	 */
	List<JuZi> findAllJuZi(JuZiSearch JuZiSearch);
	//ok
	/**
	 * 句子分页条件查询
	 * @param page
	 * @return
	 */
	List<JuZi> findJuZiByPage(Page<JuZi>page);
	//ok
	/**
	 * 通过id查找句子
	 * @param id
	 * @return
	 */
	JuZi findJuZiById(Integer id);
	/**
	 * 随机取n条记录
	 * @param integer
	 * @return
	 */
	List<JuZi> findJuZiRand(Integer rAndSize);
}

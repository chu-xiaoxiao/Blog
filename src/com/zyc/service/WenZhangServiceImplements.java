package com.zyc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zyc.mapper.WenZhangMapper;
import com.zyc.model.Page;
import com.zyc.model.WenZhang;
import com.zyc.model.WenZhangSearch;
@Service
@Transactional
public class WenZhangServiceImplements implements WenZhangService{
	@Resource
	WenZhangMapper wenZhangMapper;
	@Override
	public int addWenZhang(WenZhang wenZhang) {
		return wenZhangMapper.addWenZhang(wenZhang);
	}
	@Override
	public int modifyWenZhang(WenZhang wenZhang) {
		return wenZhangMapper.modifyWenZhang(wenZhang);
	}
	@Override
	public List<WenZhang> findAllWenZhang() {
		return wenZhangMapper.findAllWenZhang(null);
	}
	@Override
	public void deleteWenZhang(Integer id) {
		wenZhangMapper.deleteWenzhang(id);
	}
	@Override
	public WenZhang findWenZhangByid(Integer id) {
		return wenZhangMapper.findWenZhangById(id);
	}
	@Override
	public Page<WenZhang>findWenZhangBySearch(Page<WenZhang> page) {
		page.setAllPage((findWenZhangBySearch(page.getWenZhangSearch()).size()+page.getSieze()-1)/page.getSieze());
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
		List<WenZhang> wenZhangs = wenZhangMapper.findWenZhangByPage(page);
		List<WenZhang> format = new ArrayList<>();
		for(WenZhang wenZhang:wenZhangs){
			try {
				wenZhang.setWenzhangriqi(dateFormat.parse(dateFormat.format(wenZhang.getWenzhangriqi())));
				format.add(wenZhang);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		page.setLists(format);
		return page;
	}
	@Override
	public List<WenZhang> findWenZhangBySearch(WenZhangSearch wenZhangSearch) {
		return wenZhangMapper.findAllWenZhang(wenZhangSearch);
	}
	@Override
	public List<WenZhang> randWenZhang(Integer randSize) {
		return wenZhangMapper.findWenZhangRand(1);
	}
	@Override
	public Integer countWenzhang() {
		return wenZhangMapper.countWenzhang();
	}
}	

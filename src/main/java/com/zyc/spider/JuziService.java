package com.zyc.spider;

import com.zyc.model.Juzi;
import com.zyc.model.JuziExample;
import com.zyc.model.Page2;

import java.util.List;

public interface JuziService {
	void updateJuzi(String url);
	Page2<Juzi, JuziExample> findJuziByPage(Page2<Juzi, JuziExample> page2);
	Integer countJuZiByExample(JuziExample juziExample);
	List<Juzi> findall(JuziExample juziExample);
}

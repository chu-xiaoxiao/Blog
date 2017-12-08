package com.zyc.service;

import com.zyc.model.JuziTypeCount;
import com.zyc.model.JuziTypeExample;
import com.zyc.model.JuziTypeKey;

import java.util.List;

public interface JuZiTypeService {
	List<JuziTypeCount> getGruop();
	Integer countbyExample(JuziTypeExample juziTypeExample);
	List<JuziTypeKey> finAll();
}

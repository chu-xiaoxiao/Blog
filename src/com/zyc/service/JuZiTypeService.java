package com.zyc.service;

import java.util.List;
import java.util.Map;

import com.zyc.model.JuziTypeCount;
import com.zyc.model.JuziTypeExample;
import com.zyc.model.JuziTypeKey;

public interface JuZiTypeService {
	List<JuziTypeCount> getGruop();
	Integer countbyExample(JuziTypeExample juziTypeExample);
	List<JuziTypeKey> finAll();
}

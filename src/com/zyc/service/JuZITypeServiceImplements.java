package com.zyc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.zyc.mapper.JuziTypeMapper;
import com.zyc.model.JuziExample;
import com.zyc.model.JuziTypeCount;
import com.zyc.model.JuziTypeExample;
import com.zyc.model.JuziTypeKey;
@Service("juZITypeServiceImplements")
public class JuZITypeServiceImplements implements JuZiTypeService{
	@Autowired
	private JuziTypeMapper juzitypeMapper;
	@Override
	public List<JuziTypeCount> getGruop() {
		return juzitypeMapper.countType();
	}
	@Override
	public Integer countbyExample(JuziTypeExample juziTypeExample) {
		return juzitypeMapper.countByExample(juziTypeExample);
	}
	@Override
	public List<JuziTypeKey> finAll() {
		return juzitypeMapper.selectByExample(new JuziTypeExample());
	}

}

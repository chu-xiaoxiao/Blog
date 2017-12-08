package com.zyc.service;

import com.zyc.mapper.JuziTypeMapper;
import com.zyc.model.JuziTypeCount;
import com.zyc.model.JuziTypeExample;
import com.zyc.model.JuziTypeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("juZITypeServiceImplements")
public class JuZITypeServiceImplements implements JuZiTypeService {
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

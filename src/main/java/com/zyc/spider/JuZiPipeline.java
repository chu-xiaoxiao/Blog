package com.zyc.spider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zyc.mapper.JuziMapper;
import com.zyc.mapper.JuziTypeMapper;
import com.zyc.model.Juzi;
import com.zyc.model.JuziTypeExample;
import com.zyc.model.JuziTypeKey;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
@Component("juZiPipeline")
public class JuZiPipeline implements Pipeline,JuZiPipelineI{
	
	@Autowired
	private JuziMapper juziMapper;
	
	@Autowired
	private JuziTypeMapper juziTypeMapper;
	
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	public void process(ResultItems arg0, Task arg1) {
		List<Juzi> juzis = (List<Juzi>)arg0.get("juziResult");
		JuziTypeKey juziType = (JuziTypeKey)arg0.get("juziType");
	    JuziTypeExample juziTypeExample = new JuziTypeExample();

	    JuziTypeExample.Criteria criteria = juziTypeExample.createCriteria().andLeixingmingEqualTo(juziType.getLeixingming());
	    if(juziTypeMapper.selectByExample(juziTypeExample).size()>0){
	    	juziType = juziTypeMapper.selectByExample(juziTypeExample).get(0);
	    }else{
	    	juziTypeMapper.insert(juziType);
	    	juziType = juziTypeMapper.selectByExample(juziTypeExample).get(0);
	    }
	    for(Juzi temp:juzis){
	    	temp.setJuzileixing(juziType.getLeixingid());
	    	juziMapper.insert(temp);
	    }
	}
}

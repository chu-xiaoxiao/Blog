package com.zyc.mapper;

import com.zyc.model.Juzi;
import com.zyc.model.JuziExample;
import com.zyc.model.JuziTypeKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface JuziMapper {
    int countByExample(JuziExample example);

    int deleteByExample(JuziExample example);

    int deleteByPrimaryKey(Integer juziid);

    int insert(Juzi record);

    int insertSelective(Juzi record);

    List<Juzi> selectByExample(JuziExample example);

    Juzi selectByPrimaryKey(Integer juziid);

    int updateByExampleSelective(@Param("record") Juzi record, @Param("example") JuziExample example);

    int updateByExample(@Param("record") Juzi record, @Param("example") JuziExample example);

    int updateByPrimaryKeySelective(Juzi record);

    int updateByPrimaryKey(Juzi record);
    
    JuziTypeKey selectByJuzi(Integer id);
}
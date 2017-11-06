package com.zyc.mapper;

import com.zyc.model.JuziTypeCount;
import com.zyc.model.JuziTypeExample;
import com.zyc.model.JuziTypeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface JuziTypeMapper {
    int countByExample(JuziTypeExample example);

    int deleteByExample(JuziTypeExample example);

    int deleteByPrimaryKey(JuziTypeKey key);

    int insert(JuziTypeKey record);

    int insertSelective(JuziTypeKey record);

    List<JuziTypeKey> selectByExample(JuziTypeExample example);

    int updateByExampleSelective(@Param("record") JuziTypeKey record, @Param("example") JuziTypeExample example);

    int updateByExample(@Param("record") JuziTypeKey record, @Param("example") JuziTypeExample example);
    
    List<JuziTypeCount> countType();
}
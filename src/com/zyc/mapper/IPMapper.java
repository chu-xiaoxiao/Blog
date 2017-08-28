package com.zyc.mapper;

import com.zyc.model.IP;
import com.zyc.model.IPExample;
import com.zyc.model.Ip_Date;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IPMapper {
    int countByExample(IPExample example);

    int deleteByExample(IPExample example);

    int deleteByPrimaryKey(Integer ipid);

    int insert(IP record);

    int insertSelective(IP record);

    List<IP> selectByExample(IPExample example);

    IP selectByPrimaryKey(Integer ipid);

    int updateByExampleSelective(@Param("record") IP record, @Param("example") IPExample example);

    int updateByExample(@Param("record") IP record, @Param("example") IPExample example);

    int updateByPrimaryKeySelective(IP record);

    int updateByPrimaryKey(IP record);
    
    List<Ip_Date> selectCountByDay(Integer count);
}
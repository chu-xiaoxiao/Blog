package com.zyc.service;

import com.zyc.mapper.CityMapper;
import com.zyc.model.City;
import com.zyc.model.CityExample;
import com.zyc.util.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YuChen Zhang on 17/10/24.
 */
@Service("cityServiceImplements")
public class CityServiceImplement implements CityService{

    @Autowired
    CityMapper cityMapper;
    @Override
    public List<City> getNextLevel(City city) throws MyException {
        if(city.getLeveltype()==3){
            throw new MyException("当前城市没有下级城市");
        }
        CityExample cityExample = new CityExample();
        cityExample.getOredCriteria().add(cityExample.createCriteria().andParentidEqualTo(city.getId()));
        return cityMapper.selectByExample(cityExample);
    }

    @Override
    public City getCityByPrimaryKey(Integer id) {
        return cityMapper.selectByPrimaryKey(id);
    }
}

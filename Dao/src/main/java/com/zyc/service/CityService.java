package com.zyc.service;

import com.zyc.model.City;
import com.zyc.util.MyException;

import java.util.List;

/**
 * Created by YuChen Zhang on 17/10/24.
 */

public interface CityService {
    List<City> getNextLevel(City city) throws MyException;
    City getCityByPrimaryKey(Integer id);
}

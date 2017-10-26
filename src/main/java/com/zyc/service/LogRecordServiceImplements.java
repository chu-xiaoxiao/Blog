package com.zyc.service;

import com.zyc.mapper.LogrecordMapper;
import com.zyc.model.Logrecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YuChen Zhang on 17/10/26.
 */
@Service("logRecordServiceImplements")
@Transactional
public class LogRecordServiceImplements implements LogRecordService{
    @Autowired
    LogrecordMapper logrecordMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void insert(Logrecord logrecord) {
        logrecordMapper.insertSelective(logrecord);
    }
}

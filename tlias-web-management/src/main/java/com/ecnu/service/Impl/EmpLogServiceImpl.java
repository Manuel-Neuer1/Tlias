package com.ecnu.service.Impl;

import com.ecnu.mapper.EmpLogMapper;
import com.ecnu.pojo.EmpLog;
import com.ecnu.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW) //需要在员工新的事务中运行（希望两个方法在独立的事务中运行）
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}

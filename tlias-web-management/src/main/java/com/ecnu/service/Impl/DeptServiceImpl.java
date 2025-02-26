package com.ecnu.service.Impl;

import com.ecnu.mapper.DeptMapper;
import com.ecnu.pojo.Dept;
import com.ecnu.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void insert(Dept dept) {
        //补全dept中的除id以外的其他属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        System.out.println(dept);
        // 调用业务层方法插入部门数据
        deptMapper.insert(dept);
    }

    @Override
    public void update(Dept dept) {
        //补全updateTime
        dept.setUpdateTime(LocalDateTime.now());
        System.out.println(dept);
        deptMapper.update(dept);
    }

    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }


}

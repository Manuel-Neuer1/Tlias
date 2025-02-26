package com.ecnu.service;

import com.ecnu.pojo.Dept;

import java.util.List;

public interface DeptService {
    /*
     * 查询所有部门信息
     * @return 部门信息列表
     */
    List<Dept> findAll();
    /*
     * 根据 id 删除部门信息
     */
    void deleteById(Integer id);

    /*
     * 插入部门信息
     * @param dept 部门信息
     */
    void insert(Dept dept);

    void update(Dept dept);

    Dept findById(Integer id);
}

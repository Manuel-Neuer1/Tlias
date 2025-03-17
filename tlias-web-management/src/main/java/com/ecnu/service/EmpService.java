package com.ecnu.service;

import com.ecnu.pojo.Emp;
import com.ecnu.pojo.EmpQueryParam;
import com.ecnu.pojo.LoginInfo;
import com.ecnu.pojo.PageResult;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface EmpService {
    /*
     * 分页查询
     * page 页码
     * pageSize 每页最大记录数
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /*
     * 新增员工
     */
    void save(Emp emp);

    void delete(Integer[] ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);
}

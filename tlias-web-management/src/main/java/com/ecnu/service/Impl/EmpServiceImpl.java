package com.ecnu.service.Impl;

import com.ecnu.mapper.EmpExprMapper;
import com.ecnu.mapper.EmpMapper;
import com.ecnu.pojo.*;
import com.ecnu.service.EmpLogService;
import com.ecnu.service.EmpService;
import com.ecnu.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service // 注解表示这是一个 Spring 的 Service 类，
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper exprMapper;

    @Autowired
    private EmpLogService emplogService;
    @Autowired
    private EmpExprMapper empExprMapper;

    //--------原始分页查询操作------------------
//    @Override
//    public PageResult<Emp> page(String name, Integer gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
//        // 1 调用Mapper接口来查询总记录数
//        Long total = empMapper.getCount();
//        // 2 调用Mapper接口查询结果列表
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start,pageSize);
//        // 3 封装PageResult对象结果，返回
//        return new PageResult<Emp>(total,rows);
//    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 1 设置分页参数：page，pageSize -》 使用PageHelper类
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        // 2 执行查询
        List<Emp> empList = empMapper.list2(empQueryParam);
        // 3 使用PageInfo包装查询结果
        Page<Emp> p = (Page<Emp>) empList;

        // 4 封装PageResult对象结果，返回
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    //事务管理 将当前方法交给spring进行事务管理，添加位置：业务层的方法上、类上、接口上
    @Transactional //-- 默认出现运行时异常 RuntimeException 回滚
     //@Transactional(rollbackFor = {Exception.class}) //出现所有异常都回滚
     //方法执行前开启事务，方法执行后提交事务，出现异常则回滚事务
    @Override
    public void save(Emp emp) {
        try {
            // 1 保存员工基本信息
            empMapper.insert(emp); //执行完成后，主键返回，赋值给id属性
            // 1.1 创建时间和更新时间
            emp.setCreateDate(LocalDate.now());
            emp.setUpdateDate(LocalDate.now());
            // 2 保存员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if (!exprList.isEmpty()) {
                //批量保存员工的工作经历信息
                //遍历集合，为empId赋值
                exprList.forEach(expr -> {expr.setEmpId(emp.getId());});
                exprMapper.insertBatch(emp.getId(), exprList);
            }
        } finally {
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工：" + emp); // id 字段是自增的，可以set null，也可以随便给值
            //保存新增员工的日志
            emplogService.insertLog(empLog);

        }


    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(Integer[] ids) {
        //1 删除员工的基本信息
        empMapper.deleteByIds(ids);

        //2 删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        //1 根据id查询员工的基本信息
        Emp emp = empMapper.getById(id);

        return emp;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        // 1 根据ID修改员工的基本信息
        emp.setUpdateDate(LocalDate.now());
        empMapper.updateById(emp);
        // 2 根据ID删除员工的工作经历信息
        Integer Ids[] = {emp.getId()};
        empExprMapper.deleteByEmpIds(Ids);
        // 3 批量保存员工的工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if (!exprList.isEmpty()) {
            for(EmpExpr expr : exprList) {
                expr.setEmpId(emp.getId());
            }
            empExprMapper.insertBatch(emp.getId(), exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        LoginInfo loginInfo = empMapper.selectByUsernameAndPassword(emp);
        if(loginInfo != null) {

            //生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            //令牌中存储id和username 确定是哪个登录用户
            claims.put("id", emp.getId());
            claims.put("username", emp.getUsername());

            String jwtToken = JwtUtils.generateJwt(claims);
            loginInfo.setToken(jwtToken);
            log.info("登录成功，员工登录信息：{}", loginInfo);
            return loginInfo;
        }
        else return null;
    }
}

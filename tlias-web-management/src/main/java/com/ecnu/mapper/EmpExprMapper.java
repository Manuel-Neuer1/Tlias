package com.ecnu.mapper;

import com.ecnu.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 * @Mapper 注解表示这是一个 MyBatis 的 Mapper 接口，
 * 这个是员工工作经历的Mapper接口
 */
@Mapper
public interface EmpExprMapper {

    /*
     *批量保存员工的工作经历信息
     */
    void insertBatch(Integer empId,List<EmpExpr> exprList);


    void deleteByEmpIds(Integer[] empIds);
}

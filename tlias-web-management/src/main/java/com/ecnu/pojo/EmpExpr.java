package com.ecnu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data // 自动生成所有参数的getter和setter方法
@AllArgsConstructor // 自动生成全参构造方法
@NoArgsConstructor // 自动生成无参构造方法
public class EmpExpr {
    private Integer id; //ID，主键
    private Integer empId; //员工ID
    private LocalDate begin; //开始时间
    private LocalDate end; //结束时间
    private String company; //公司名称
    private String job; //职位
}

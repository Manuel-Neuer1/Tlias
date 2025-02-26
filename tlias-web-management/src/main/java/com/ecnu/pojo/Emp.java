package com.ecnu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data // 自动生成所有参数的getter和setter方法
@AllArgsConstructor // 自动生成全参构造方法
@NoArgsConstructor // 自动生成无参构造方法
public class Emp {
    private Integer id; //ID，主键
    private String username; //用户名
    private String password; //密码
    private String name; //姓名
    private Integer gender; //性别：1 男，2 女
    private String phone; //手机号
    private Integer job; //职位：1 班主任，2 讲师，3 学工主管，4 教研主管，5 咨询师
    private Integer salary; //薪资
    private String image; //头像
    private LocalDate entryDate; //入职时间
    private Integer deptId; //关联部门ID
    private LocalDate createDate; //创建时间
    private LocalDate updateDate; //修改时间

    private String deptName;//关联Dept表的name属性

    //封装工作经历信息
    private List<EmpExpr> exprList;
}

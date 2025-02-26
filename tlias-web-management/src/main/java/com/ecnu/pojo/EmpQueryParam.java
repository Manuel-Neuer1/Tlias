package com.ecnu.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    String name;//员工姓名
    Integer gender;//员工性别

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    LocalDate begin;//查询的起始时间

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    LocalDate end;//查询的结束时间
    Integer page = 1; //默认当前页码为1
    Integer pageSize = 10;//默认每页记录数为10
}

package com.ecnu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // 自动生成所有参数的getter和setter方法
@NoArgsConstructor // 自动生成无参构造方法
@AllArgsConstructor // 自动生成全参构造方法
public class Dept {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

package com.ecnu.controller;

/*
*  登录Controller
* */

import com.ecnu.pojo.Emp;
import com.ecnu.pojo.LoginInfo;
import com.ecnu.pojo.Result;
import com.ecnu.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录：{}", emp.toString());
        LoginInfo loginInfo = empService.login(emp);
        if(loginInfo != null) return Result.success(loginInfo);
        else return Result.error("用户名或密码错误！");
    }
}

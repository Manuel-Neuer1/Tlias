package com.ecnu.controller;

import com.ecnu.pojo.Emp;
import com.ecnu.pojo.EmpQueryParam;
import com.ecnu.pojo.PageResult;
import com.ecnu.pojo.Result;
import com.ecnu.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;

/*
 * @RestController 注解表示这是一个 Spring 的 RestController 类，
 * 这个是员工管理的controller类
 */
@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    //分页查询
    @GetMapping("/emps")
    public Result page(/*@RequestParam(required = false) String name,
                       @RequestParam(required = false) Integer gender,
                       @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize*/
                        EmpQueryParam empQueryParam) {
        //log.info("分页查询 --> name:{}, gender:{}, begin:{}, end:{}, page:{}, pageSize:{}", name, gender, begin, end, page, pageSize);
        log.info("分页查询 empQueryParam:{}", empQueryParam);
        //PageResult<Emp> pageRes = empService.page(name, gender, begin, end, page, pageSize);
        PageResult<Emp> pageRes = empService.page(empQueryParam);
        return Result.success(pageRes);
    }

    /*
     * 新增员工
     */
    @PostMapping("/emps")
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工信息 --> emp:{}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     *
     * @param ids
     * 第一种方法delete(Integer[] ids)
     * 第二种方法delete(@RequestParam List<Integer> ids)
     */
     @DeleteMapping("/emps")
    public Result delete(Integer[] ids) {
        log.info("删除员工信息 --> ids:{}", Arrays.toString(ids));

        //调用Service层的删除方法
         empService.delete(ids);
        return Result.success();
     }

     // 根据ID查询员工信息
     @GetMapping("/emps/{id}")
     public Result getInfo(@PathVariable Integer id) {
        log.info("接收到的id:{}", id);
        Emp emp = empService.getInfo(id);
         return Result.success(emp);
     }

     @PutMapping("/emps")
    public Result update(@RequestBody Emp emp) {
         log.info("修改员工信息--> emp:{}", emp);
         empService.update(emp);
         return Result.success(emp);
     }

}

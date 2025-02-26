package com.ecnu.controller;


import com.ecnu.pojo.Dept;
import com.ecnu.pojo.Result;
import com.ecnu.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 当前是一个Restful风格的Controller，负责处理接收请求，调用Service层，返回响应结果
public class DeptController {

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list(){
        System.out.println("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();

        return Result.success(deptList);

    }

    //通过URL直接传递参数，使用{...}来标识该路径参数，需要使用@PathVariable注解来获取{...}中的路径参数
    @GetMapping("/depts/{id}")
    public Result findById(@PathVariable("id") Integer id){
        System.out.println("根据 id 查询部门数据：" + id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    /*
     * 删除部门 - 方式一：HttpServletRequest 获取请求参数 （繁琐不推荐）

    @DeleteMapping("/depts")
    public Result deleteById(HttpServletRequest request){
        String idStr = request.getParameter("id");
        Integer id = Integer.parseInt(idStr);
        System.out.println("根据 id 删除部门数据：" + id);
        // 调用业务层方法删除部门数据
        deptService.deleteById(id);
        return Result.success();
    }
    */

    /*
     * 删除部门 - 方式二：@RequestParam 获取请求参数 （推荐）
     * @RequestParam 注解用于将请求参数绑定到方法参数上，可以指定参数名称，如果不指定，则默认使用参数名称
     * @RequestParam 注解还可以指定 required 属性，用于指定参数是否是必须的，默认为 true，如果请求中没有该参数，则会抛出异常
     * 如果请求参数名与形参变量名相同，直接定义方法形参即可接收：public Result deleteById(Integer id)
     * */
    @DeleteMapping("/depts")
    public Result deleteById(@RequestParam(value = "id",required = true) Integer id){
        System.out.println("根据 id 删除部门数据：" + id);
        deptService.deleteById(id);
        return Result.success();
    }


    @PostMapping("/depts")
    //@RequestBody 注解用于将请求体中的 JSON 数据绑定到方法参数上
    public Result insert(@RequestBody Dept dept) {
        System.out.println("插入部门数据：" + dept);
        deptService.insert(dept);
        return Result.success();
    }

    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        System.out.println("更新部门数据：" + dept);
        deptService.update(dept);
        return Result.success();
    }
}

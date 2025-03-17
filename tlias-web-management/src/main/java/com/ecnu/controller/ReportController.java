package com.ecnu.controller;

import com.ecnu.pojo.JobOption;
import com.ecnu.pojo.Result;
import com.ecnu.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report") //所有请求都是/report路径下的
public class ReportController {
    @Autowired
    private ReportService reportService;
    /**
     * 统计员工职位人数
     */

    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计员工人数！");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("统计员工性别人数！");
        List<Map<String,Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }
}

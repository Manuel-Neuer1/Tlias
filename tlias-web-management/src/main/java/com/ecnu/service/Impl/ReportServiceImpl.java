package com.ecnu.service.Impl;

import com.ecnu.mapper.EmpMapper;
import com.ecnu.pojo.JobOption;
import com.ecnu.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {
        // 1 调用mapper 接口，获得统计数据
        List<Map<String,Object>> list = empMapper.countEmpJobData(); //map:pos=xxx,num=xxx;...

        // 2 组装数据并返回
        List<Object> posList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        JobOption jobOption = new JobOption(posList, dataList);

        return jobOption;
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();

    }


}

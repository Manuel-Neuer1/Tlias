package com.ecnu.controller;

import com.ecnu.pojo.Result;
import com.ecnu.utils.AliyunOSSOperator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class  UploadController {

    /**
     * 本地磁盘存储的方案（不推荐）
     * @param name
     * @param age
     * @param file
     * @return
     * @throws IOException
     */
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("接收到的参数 --> name:{}, age:{}, file:{}", name, age, file);
//        //获取原始文件名（但是如果多用户上传会导致上传的文件有重名可能，这种方法不好）
//        String originalFilename = file.getOriginalFilename();
//
//        //使用java.util.UUID 生成一个随机的字符串作为文件名
//        String newFilename = UUID.randomUUID().toString() + "." + originalFilename.substring(originalFilename.lastIndexOf("."));
//
//        //保存文件(将上传的文件保存到服务器（这里是localhost）指定的目录下,默认上传文件大小最大为 1MB，可以在application.yml中配置)
//        file.transferTo(new File("D:\\JavaDevelop\\Project\\web-ai-code\\web-ai-project\\image" + originalFilename));
//        return Result.success();
//    }

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    /**
     * 阿里云OSS存储方案
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file.getOriginalFilename());

        //将文件交给OSS管理
        String url = aliyunOSSOperator.upload(file.getBytes(),file.getOriginalFilename());
        log.info("文件上传OSS成功：{}",url);

        return Result.success(url);
    }

}

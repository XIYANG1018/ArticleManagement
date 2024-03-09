package org.example.controller;


import org.example.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException { // "I/O exception" 是输入/输出异常（Input/Output Exception）
        // store the file content into local storage 缺点是本地存储不能访问

        String originalFileName = file.getOriginalFilename();

        // 保证文件名字唯一，借助UUID, 拼接UUID和文件名
        // 找文件名后缀： 用lastIndexOf找最后一个.
        assert originalFileName != null;
        String filename = UUID.randomUUID()+originalFileName.substring(originalFileName.lastIndexOf("."));

        file.transferTo(new File("C:\\springbootcode\\artical_management\\files\\"+filename)); // finish storage

        // return to front-end response
        return Result.success("url address");

    }
}

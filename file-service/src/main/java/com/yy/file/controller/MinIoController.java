package com.yy.file.controller;

import com.yy.file.util.MinIoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author yy
 * @date 2022/4/14 16:46
 */
@RestController
@RequestMapping("/file")
public class MinIoController {
    @Resource
    private MinIoTemplate minIoTemplate;

    @PostMapping("/create/bucket")
    String create(String bucketName) {
        try {
            minIoTemplate.makeBucket(bucketName);
        } catch (Exception e) {
            return "创建失败";
        }
        return "创建成功";
    }

    @PostMapping("/put/object")
    String putObject(MultipartFile uploadFile, @RequestParam String bucketName) {
        String originalFilename = uploadFile.getOriginalFilename();
        try {
            minIoTemplate.putObject(bucketName, originalFilename, uploadFile.getInputStream());
        } catch (Exception e) {
            System.out.println(e);
            return "创建失败";
        }
        return "http://120.78.125.171:9000/" + bucketName + "/" + originalFilename;
    }
}

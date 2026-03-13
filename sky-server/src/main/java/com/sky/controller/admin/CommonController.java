package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {

    @Value("${sky.upload-path}")
    private String uploadPath;

    @Value("${sky.image-url}")
    private String imageUrl;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) throws IOException, IOException {

        log.info("文件上传：{}", file);

        // 1 获取原始文件名
        String originalFilename = file.getOriginalFilename();

        // 2 获取文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 3 生成唯一文件名
        String fileName = UUID.randomUUID().toString() + suffix;

        // 4 创建文件对象
        File dest = new File(uploadPath + fileName);

        // 如果目录不存在就创建
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }

        // 5 保存文件
        file.transferTo(dest);

        // 6 生成访问路径
        String url = imageUrl + fileName;

        return Result.success(url);
    }
}

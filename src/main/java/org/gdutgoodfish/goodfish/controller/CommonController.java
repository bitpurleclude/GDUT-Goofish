package org.gdutgoodfish.goodfish.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.gdutgoodfish.goodfish.util.FileUploadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class CommonController {

    private final FileUploadUtil fileUploadUtil;

    @PostMapping("/upload")
    public Result<String> uploadItem(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("文件上传");
        String path = fileUploadUtil.uploadFileToOss(file);
        log.info("文件链接：{}", path);
        return Result.success(path, "图片上传成功");
    }
}

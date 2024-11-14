package org.gdutgoodfish.goodfish.util;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.gdutgoodfish.goodfish.exception.BaseException;
import org.gdutgoodfish.goodfish.exception.CommonException.UploadException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
@Data
@ConfigurationProperties(prefix = "aliyunoss")
public class FileUploadUtil {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    public String uploadFileToOss(MultipartFile file) {
        if (null == file || null == file.getOriginalFilename()) {
            throw new UploadException("图片上传失败");
        }
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID() + extension;
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {

            // 上传文件
            ossClient.putObject(bucketName, fileName, file.getInputStream());
            // 返回文件的URL
            return "https://" + bucketName + "." + endpoint + "/" + fileName;
        } catch (IOException e) {
            throw new BaseException("文件上传异常");
        } finally {
            // 关闭OSSClient
            ossClient.shutdown();
        }
    }

}

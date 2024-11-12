package org.gdutgoodfish.goodfish.util;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.UUID;

@Component
@Data
@ConfigurationProperties(prefix = "oss")
public class FileUploadUtil {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    public String uploadFileToOss(InputStream inputStream, String originalFilename) {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 生成唯一文件名
            String filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            // 上传文件
            ossClient.putObject(bucketName, filename, inputStream);
            // 返回文件的URL
            return "https://" + bucketName + "." + endpoint + "/" + filename;
        } finally {
            // 关闭OSSClient
            ossClient.shutdown();
        }
    }

}

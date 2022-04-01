package cn.baizhi.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class VideoUpLoad {

    public static void uploadVideo(MultipartFile file){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "";
        String accessKeySecret = "";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

// 上传Byte数组。
        byte[] content = new byte[0];
        try {
            content = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossClient.putObject("yizhijiaozi", "video/"+file.getOriginalFilename(), new ByteArrayInputStream(content));

// 关闭OSSClient。
        ossClient.shutdown();
    }
}

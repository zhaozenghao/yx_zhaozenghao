package cn.baizhi.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

public class DeleteFile {
    public static void deleteFile(String name){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI5tQVcKgHXQRSZQevAbX4";
        String accessKeySecret = "YAQF4gF8abLz9mXF4mW7Fo7HzdBqXH";

// 填写Bucket名称。
        String bucketName = "yizhijiaozi";
// 填写文件完整路径。文件完整路径中不能包含Bucket名称。
        String objectName = name;

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 删除文件或目录。如果要删除目录，目录必须为空。
        ossClient.deleteObject(bucketName, objectName);

// 关闭OSSClient。
        ossClient.shutdown();

    }
}

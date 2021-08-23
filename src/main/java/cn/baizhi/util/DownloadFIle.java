package cn.baizhi.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;

import java.io.File;

public class DownloadFIle {

    public static void downloadFile(String fileName){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI5tQVcKgHXQRSZQevAbX4";
        String accessKeySecret = "YAQF4gF8abLz9mXF4mW7Fo7HzdBqXH";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

        // 填写Bucket名称。
        String bucketName = "yizhijiaozi";
// 填写不包含Bucket名称在内的Object完整路径，例如testfolder/exampleobject.txt。
        String objectName = fileName;
// 下载Object到本地文件，并保存到指定的本地路径中。如果指定的本地文件存在会覆盖，不存在则新建。
// 如果未指定本地路径，则下载后的文件默认保存到示例程序所属项目对应本地路径中。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File("D:\\IDEACodes\\yx_zhaozenghao\\src\\main\\webapps\\"+fileName));

// 关闭OSSClient。
        ossClient.shutdown();
    }
}

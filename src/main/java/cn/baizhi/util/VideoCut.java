package cn.baizhi.util;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class VideoCut {

    public static void videoCut(MultipartFile file){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI5tQVcKgHXQRSZQevAbX4";
        String accessKeySecret = "YAQF4gF8abLz9mXF4mW7Fo7HzdBqXH";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

        String bucketName = "yizhijiaozi";
// 填写视频文件的完整路径。若视频文件不在Bucket根目录，需携带文件访问路径，例如examplefolder/videotest.mp4。
        String objectName = "video/"+file.getOriginalFilename();
// 使用精确时间模式截取视频50s处的内容，输出为JPG格式的图片，宽度为800，高度为600。
        String style = "video/snapshot,t_1000,f_jpg,w_800,h_600";
// 指定过期时间为10分钟。
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);

        // 填写网络流地址。
        InputStream inputStream = null;
        try {
            inputStream = new URL(signedUrl.toString()).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String name = "video/"+file.getOriginalFilename().replace(".mp4",".jpg");
        ossClient.putObject(bucketName, name, inputStream);
// 关闭OSSClient。
        ossClient.shutdown();
    }
}

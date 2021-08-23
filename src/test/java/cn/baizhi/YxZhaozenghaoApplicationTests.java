package cn.baizhi;

import cn.baizhi.dao.AdminDao;
import cn.baizhi.entity.Admin;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;

@SpringBootTest
class YxZhaozenghaoApplicationTests {

    @Autowired
    private AdminDao adminDao;
    @Test
    void contextLoads() {
        Admin admin = adminDao.queryByUserName("阿珍");
        System.out.println(admin);
    }

    @Test
    public void test(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI5tQVcKgHXQRSZQevAbX4";
        String accessKeySecret = "YAQF4gF8abLz9mXF4mW7Fo7HzdBqXH";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

// 上传Byte数组。
        byte[] content = "Hello OSS".getBytes();
        ossClient.putObject("yizhijiaozi", "yi/example.text", new ByteArrayInputStream(content));

// 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void test3(){
        System.out.println("http://yizhijiaozi.oss-cn-beijing.aliyuncs.com/".length());
        System.out.println("http://yizhijiaozi.oss-cn-beijing.aliyuncs.com/6efa4514c0b14e7b463651fbb9c9e03.png".substring(47));
    }

    @Test
    public void test4(){
        System.out.println("http://yizhijiaozi.oss-cn-beijing.aliyuncs.com".replace(".com", ".jpg"));
    }

}

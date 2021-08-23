package cn.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.baizhi.entity.User;
import cn.baizhi.service.MouthAndCountService;
import cn.baizhi.service.UserService;
import cn.baizhi.util.DownloadFIle;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    private Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService us;
    @Autowired
    private MouthAndCountService mc;

    @RequestMapping("/queryByPage")
    public Map<String, Object> queryByPage(int page){
        int size = 3;
        return us.queryByPage(page,size);

    }
    @RequestMapping("/freeze")
    public String  freeze(String id,int statu){
        log.debug(id);
        log.debug(statu+"zhuangtai");
        us.freezeUser(id, statu);
        return null;
    }

    //添加用户
    @RequestMapping("/add")
    public void add(MultipartFile photo,String username,String phone,String brief){
        User user = new User(null,username,phone,"http://yizhijiaozi.oss-cn-beijing.aliyuncs.com/"+photo.getOriginalFilename(),brief,null,new Date(),0);
        us.insertUser(user,photo);
    }

    //删除用户
    @RequestMapping("/delete")
    public void delete(String id,String headimg){
        us.deleteUser(id,headimg);
    }


    //导出用户
    @RequestMapping("/exportMessage")
    public void exportMessage() throws IOException {
        List<User> users = us.findAll();
        for (User user : users) {
            String substring = user.getHeadimg().substring(user.getHeadimg().lastIndexOf("/") + 1);
            DownloadFIle.downloadFile(substring);
            user.setHeadimg("D:\\IDEACodes\\yx_zhaozenghao\\src\\main\\webapps\\"+substring);
        }

        //参数：(一级标题，二级标题，表名)，实体类类对象，导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("一中的崽子们","学生表"),User.class, users);

        workbook.write(new FileOutputStream(new File("D:/exportedExcel/user.xls")));
        //释放资源
        workbook.close();
    }

    @RequestMapping("UserCount")
    public Map<String, Object> userCount(){
        Map<String, Object> map = us.goEasy();
        return map;
    }

}

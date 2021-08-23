package cn.baizhi.service;

import cn.baizhi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService {
    //分页查业务
    Map<String,Object> queryByPage(int page,int size);

    //查所有
    List<User> findAll();

    //冻结业务
    void freezeUser(String id,int statu);

    //添加用户
    void insertUser(User user, MultipartFile photo);

    //删除用户
    void deleteUser(String id,String headimg);

    //
    Map<String, Object> goEasy();
}

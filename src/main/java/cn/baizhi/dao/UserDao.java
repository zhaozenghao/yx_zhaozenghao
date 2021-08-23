package cn.baizhi.dao;

import cn.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    // 范围查
    List<User> queryRange(@Param("start") int start,@Param("end") int end);

    //查所有
    List<User> queryAll();
    //查总人数
    Integer queryCount();
    //修改状态
    void changeStatu(@Param("id") String id,@Param("statu") int statu);
    //添加用户
    void insertUser(User user);
    //删除用户
    void deleteUser(String id);
}

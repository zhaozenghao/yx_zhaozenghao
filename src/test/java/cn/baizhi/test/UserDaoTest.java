package cn.baizhi.test;


import cn.baizhi.dao.UserDao;
import cn.baizhi.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao ud;
    @Test
    public void testQueryRange(){
        List<User> list = ud.queryRange(0, 3);
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void queryCount(){
        Integer integer = ud.queryCount();
        System.out.println(integer);
    }
    @Test
    public void freezeUser(){
       ud.changeStatu("1",0);
    }
}

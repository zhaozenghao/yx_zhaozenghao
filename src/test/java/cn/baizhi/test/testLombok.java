package cn.baizhi.test;

import cn.baizhi.entity.Admin;
import lombok.extern.java.Log;

@Log
public class testLombok {
    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.setId("1");
        admin.setUsername("zhaozijing");
        System.out.println(admin);
        System.out.println(admin.getUsername());
        System.out.println(admin.toString());
        Admin admin1 = new Admin("1", "zhaozijing", "122341", 1);
        System.out.println(admin1.toString());
    }
}

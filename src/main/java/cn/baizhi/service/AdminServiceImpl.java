package cn.baizhi.service;

import cn.baizhi.dao.AdminDao;
import cn.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> login(String username, String password) {
        Map<String,Object> map = new HashMap<>();

        Admin admin = adminDao.queryByUserName(username);

        if(admin!=null){
            if(admin.getPassword().equals(password)){
                map.put("flag",true);
                map.put("msg","登陆成功");
            }else{
                map.put("flag",false);
                map.put("msg","密码错误");
            }
        }else{
            map.put("flag",false);
            map.put("msg","用户名错误");
        }
        return map;
    }
}

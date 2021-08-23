package cn.baizhi.service;

import cn.baizhi.annotation.DeleteCache;
import cn.baizhi.dao.MouthAndCountDao;
import cn.baizhi.dao.UserDao;
import cn.baizhi.entity.User;
import cn.baizhi.util.DeleteFile;
import cn.baizhi.util.UploadFile;
import cn.baizhi.vo.MouthAndCount;
import com.alibaba.fastjson.JSONObject;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao ud;
    @Autowired
    private MouthAndCountDao mc;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryByPage(int page, int size) {
        Map<String, Object> map = new HashMap<>();
        List<User> list = ud.queryRange((page - 1) * size, size);
        Integer count = ud.queryCount();
        Integer a = null;
        if(count%size ==0){
            a = count/size;
        }else {
            a = count/size+1;
        }

        map.put("data",list);
        map.put("page",page);
        map.put("count",a);
        return map;
    }

    @Override
    @DeleteCache
    public void freezeUser(String id, int statu) {
        if(statu==1){
            ud.changeStatu(id,0);
        }else{
            ud.changeStatu(id,1);
        }
    }

    @Override
    @DeleteCache
    public void insertUser(User user, MultipartFile photo) {
        user.setId(UUID.randomUUID().toString());
        ud.insertUser(user);
        UploadFile.upload(photo);
    }

    @Override
    @DeleteCache
    public void deleteUser(String id,String headimg) {
        ud.deleteUser(id);
        DeleteFile.deleteFile(headimg.substring(47));

    }
    //查所有
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAll() {
        return ud.queryAll();
    }

    @Override
    public Map<String, Object> goEasy() {
        List<String> data = Arrays.asList("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月");
        List<MouthAndCount> frozen = mc.queryFrozen();
        List<MouthAndCount> available = mc.queryAvailable();
        int[] aa = new int[12];
        int[] bb = new int[12];
        //遍历月份

        for(int i = 1;i<=12;i++){
            for (MouthAndCount count : frozen) {
                if (i == count.getMonth()){
                    aa[i-1]=count.getCount();
                    break;
                }else {
                    aa[i-1]=0;
                }
            }
        }

        for (int i = 1;i<=12;i++){
            for (MouthAndCount count : available) {
                if (i ==count.getMonth()){
                    bb[i-1] = count.getCount();
                    break;
                }else {
                    bb[i-1]= 0;
                }
            }
        }

        List<int[]> list = Arrays.asList(aa);
        List<int[]> asList = Arrays.asList(bb);
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("manCount", list);
        map.put("womanCount", asList);


        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-4130293a45f4454bbaeaeb259d0b6da1");
                goEasy.publish("aa", JSONObject.toJSONString(map));

        return map;
    }
}

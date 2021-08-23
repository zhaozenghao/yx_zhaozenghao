package cn.baizhi.test;

import com.alibaba.fastjson.JSONObject;
import io.goeasy.GoEasy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


@SpringBootTest
public class TestGoEasy {

    @Test
    public void goeasy(){
        Map<String, Object> map = new HashMap<>();

        List<String> data = Arrays.asList("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月");

        Random random = new Random();
        for(int i = 0;i<100;i++){
            List<Integer> list = Arrays.asList(random.nextInt(100), random.nextInt(100),random.nextInt(100),random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100));
            List<Integer> asList = Arrays.asList(random.nextInt(100), random.nextInt(100),random.nextInt(100),random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100));

            map.put("data", data);
            map.put("manCount", list);
            map.put("womanCount", asList);
            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-4130293a45f4454bbaeaeb259d0b6da1");
            goEasy.publish("aa", JSONObject.toJSONString(map));
        }

    }
}

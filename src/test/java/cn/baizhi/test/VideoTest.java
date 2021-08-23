package cn.baizhi.test;

import cn.baizhi.dao.VideoDao;
import cn.baizhi.entity.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class VideoTest {

    @Autowired
    private VideoDao vd;
    @Test
    public void testFindAll(){
        List<Video> videos = vd.selectAll(0, 1);
        for (Video video : videos) {
            System.out.println(video);
        }
    }

}

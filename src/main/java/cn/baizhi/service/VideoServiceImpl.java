package cn.baizhi.service;

import cn.baizhi.annotation.DeleteCache;
import cn.baizhi.dao.VideoDao;
import cn.baizhi.entity.User;
import cn.baizhi.entity.Video;
import cn.baizhi.util.DeleteFile;
import cn.baizhi.util.VideoCut;
import cn.baizhi.util.VideoUpLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao vd;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryByPage(int page, int size) {
        Map<String, Object> map = new HashMap<>();
        List<Video> list = vd.selectAll((page - 1) * size, size);
        Integer count = vd.queryCount();
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

//    添加视频
    @Override
    @DeleteCache
    public void insertVideo(Video video, MultipartFile file) {
        video.setId(UUID.randomUUID().toString());
        vd.insertVideo(video);
        VideoUpLoad.uploadVideo(file);
        VideoCut.videoCut(file);
    }

    //删除视频
    @Override
    @DeleteCache
    public void deleteVideo(String id, String path) {
        vd.deleteVideo(id);
        String videoPath ="video/"+path.substring(path.lastIndexOf("/")+1);
        String coverPath = videoPath.replace(".mp4",".jpg");
        DeleteFile.deleteFile(videoPath);
        DeleteFile.deleteFile(coverPath);
    }
}

package cn.baizhi.service;

import cn.baizhi.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface VideoService {
    //分页查所有
    Map<String,Object> queryByPage(int page, int size);

    //添加视频
    void insertVideo(Video video, MultipartFile file);

    //删除视频
    void deleteVideo(String id,String path);
}

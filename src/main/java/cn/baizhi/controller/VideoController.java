package cn.baizhi.controller;


import cn.baizhi.entity.Category;
import cn.baizhi.entity.Video;
import cn.baizhi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService vs;

    @RequestMapping("/queryByPage")
    public Map<String,Object> queryByPage(Integer page){
        int size = 3;
        return vs.queryByPage(page,size);
    }

    @RequestMapping("/add")
    public void add(MultipartFile video,String title,String brief,String id){
        String coverPath = "http://yizhijiaozi.oss-cn-beijing.aliyuncs.com/video/"+video.getOriginalFilename().replace(".mp4",".jpg");
        String videoPath = "http://yizhijiaozi.oss-cn-beijing.aliyuncs.com/video/"+video.getOriginalFilename();
        Category category = new Category();
        category.setId(id);
        Video video1 = new Video(null, title, brief, coverPath, videoPath, new Date(), category, null, null);
        vs.insertVideo(video1,video);
    }

    @RequestMapping("/delete")
    public void delete(String id,String videoPath){
        vs.deleteVideo(id,videoPath);
    }
}

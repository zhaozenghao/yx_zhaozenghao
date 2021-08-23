package cn.baizhi.dao;

import cn.baizhi.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao {
    //分页查所有
    List<Video> selectAll(@Param("start") int start, @Param("end") int end);

    //查总数
    Integer queryCount();

    //添加视频
    void insertVideo(Video video);

    //删除视频
    void deleteVideo(String id);
}

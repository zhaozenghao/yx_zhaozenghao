package cn.baizhi.dao;

import cn.baizhi.vo.MouthAndCount;

import java.util.List;

public interface MouthAndCountDao {


    List<MouthAndCount> queryFrozen();

    List<MouthAndCount> queryAvailable();
}

package cn.baizhi.service;



import cn.baizhi.vo.MouthAndCount;

import java.util.List;

public interface MouthAndCountService {

    List<MouthAndCount> queryFrozen();

    List<MouthAndCount> queryAvailable();
}

package cn.baizhi.service;


import cn.baizhi.dao.MouthAndCountDao;
import cn.baizhi.vo.MouthAndCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MouthAndCountServiceImpl implements MouthAndCountService{
    @Autowired
    private MouthAndCountDao mc;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<MouthAndCount> queryFrozen() {
        return mc.queryFrozen();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<MouthAndCount> queryAvailable() {
        return mc.queryAvailable();
    }
}

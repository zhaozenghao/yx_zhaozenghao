package cn.baizhi.service;

import cn.baizhi.annotation.DeleteCache;
import cn.baizhi.dao.CategoryDao;
import cn.baizhi.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryDao cd;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryByLevels(String level) {
        return cd.queryByLevels(level);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryByParentId(String id) {
        return cd.queryByParentId(id);
    }

    @Override
    @DeleteCache
    public void save(Category category) {
        category.setId(UUID.randomUUID().toString());
        cd.save(category);
    }

    @Override
    @DeleteCache
    public void delete(String id) {
        cd.delete(id);
    }

    @Override
    @DeleteCache
    public void saveHigh(Category category) {
        category.setId(UUID.randomUUID().toString());
        cd.saveHigh(category);
    }

    @Override
    @DeleteCache
    public Map<String,Object> deleteHigh(String id) {
        Map<String,Object> map=new HashMap<>();
        List<Category> list = cd.queryByParentId(id);
        if(list.isEmpty()){
            cd.delete(id);
            map.put("flag",true);
        }else{
           map.put("msg","有内容，不能删");
        }
        return map;
    }

    @Override
    @DeleteCache
    public void updateLow(Category category) {
        cd.uodateLow(category);
    }
}

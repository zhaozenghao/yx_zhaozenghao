package cn.baizhi.service;

import cn.baizhi.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    //根据level 查询
    List<Category> queryByLevels(String level);

    //根据parent_id 查询
    List<Category> queryByParentId(String id);

    //添加
    void save(Category category);

    //删除二级类别
    void delete(String id);
    //添加一级分类
    void saveHigh(Category category);

    //删除一级类别
    Map<String,Object> deleteHigh(String id);

    //修改二级类别
    void updateLow(Category category);

}

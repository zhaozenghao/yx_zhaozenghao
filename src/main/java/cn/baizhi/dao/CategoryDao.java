package cn.baizhi.dao;

import cn.baizhi.entity.Category;

import java.util.List;

public interface CategoryDao {

    //根据id查询
    List<Category> queryByLevels(String level);

    //根据parent_id 查询
    List<Category> queryByParentId(String id);
    //添加二级分类
    void save(Category category);

    //删除二级类别
    void delete(String id);

    //添加一级分类
    void saveHigh(Category category);

    //修改二级类别】
    void uodateLow(Category category);
}

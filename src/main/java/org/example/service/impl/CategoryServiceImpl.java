package org.example.service.impl;

import org.example.mapper.CategoryMapper;
import org.example.pojo.Category;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.FloatControl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        // add attributes
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        int userId = (int)map.get("id");
        category.setCreateUser(userId);
        categoryMapper.add(category);

    }

    @Override
    public List<Category> list() {
        Map<String, Object> map = ThreadLocalUtil.get();
        int userId = (int)map.get("id");

        return categoryMapper.list(userId); // only get the current loginUser's list
    }

    @Override
    public Category findById(int id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void delete(int id) {
        categoryMapper.delete(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }


}

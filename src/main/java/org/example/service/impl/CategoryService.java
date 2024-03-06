package org.example.service.impl;

import org.example.pojo.Category;

import java.util.List;

public interface CategoryService {
    void update(Category category);

    void add(Category category);

    List<Category> list();

    Category findById(int id);

    void delete(int id);
}

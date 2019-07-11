package com.trongdung.website.dao;

import com.trongdung.website.entity.CategoryEntity;

import java.util.List;

public interface CategoryDAO {
    void add (CategoryEntity categoryEntity);
    void edit(CategoryEntity categoryEntity);
    void delete(CategoryEntity categoryEntity);
    CategoryEntity getById(int id);
    CategoryEntity getByName(String name);
    List<CategoryEntity> getAll();
}

package com.trongdung.website.service;

import com.trongdung.website.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    void add(CategoryDTO categoryDTO);
    void edit(CategoryDTO categoryDTO);
    void delete(CategoryDTO categoryDTO);
    CategoryDTO getById(int id);
    CategoryDTO getByName(String name);
    List<CategoryDTO> getAll();
}

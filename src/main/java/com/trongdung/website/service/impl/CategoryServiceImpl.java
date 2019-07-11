package com.trongdung.website.service.impl;

import com.trongdung.website.dao.CategoryDAO;
import com.trongdung.website.entity.CategoryEntity;
import com.trongdung.website.model.CategoryDTO;
import com.trongdung.website.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public void add(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDTO.getName());
        categoryDAO.add(categoryEntity);
    }

    @Override
    public void edit(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryDAO.getById(categoryDTO.getId());
        if (categoryEntity != null) {
            categoryEntity.setName(categoryDTO.getName());
            categoryDAO.edit(categoryEntity);
        }
    }

    @Override
    public void delete(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryDAO.getById(categoryDTO.getId());
        categoryDAO.delete(categoryEntity);
    }

    @Override
    public CategoryDTO getById(int id) {
        CategoryEntity categoryEntity = categoryDAO.getById(id);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setName(categoryEntity.getName());
        return categoryDTO;
    }

    @Override
    public CategoryDTO getByName(String name) {
        CategoryEntity categoryEntity = categoryDAO.getByName(name);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setName(categoryEntity.getName());
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getAll() {
        List<CategoryEntity> categoryEntities = categoryDAO.getAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (CategoryEntity c : categoryEntities) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(c.getId());
            categoryDTO.setName(c.getName());
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }
}

package com.trongdung.website.dao;

import com.trongdung.website.entity.ProductEntity;

import java.util.List;

public interface ProductDAO {
    int add (ProductEntity productEntity);
    void edit (ProductEntity productEntity);
    void delete(ProductEntity productEntity);

    ProductEntity getById(int id);
    List<ProductEntity> getByName(String name);

    List<ProductEntity> getAll();
    List<ProductEntity> getByCategory();
}

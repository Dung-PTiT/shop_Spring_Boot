package com.trongdung.website.dao;

import com.trongdung.website.entity.CartEntity;

import java.util.List;

public interface CartDAO {
    int add(CartEntity cartEntity);
    void edit(CartEntity cartEntity);
    void delete(CartEntity cartEntity);
    CartEntity getById(int id);
    List<CartEntity> getAll();
}

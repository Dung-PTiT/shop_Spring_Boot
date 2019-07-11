package com.trongdung.website.dao;

import com.trongdung.website.entity.CartItemEntity;

import java.util.List;

public interface CartItemDAO {
    int add(CartItemEntity cartItemEntity);

    void edit(CartItemEntity cartItemEntity);

    void delete(CartItemEntity cartItemEntity);

    CartItemEntity getById(int id);

    List<CartItemEntity> getAll();
}

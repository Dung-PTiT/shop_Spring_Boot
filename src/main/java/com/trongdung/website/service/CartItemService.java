package com.trongdung.website.service;

import com.trongdung.website.model.CartItemDTO;

import java.util.List;

public interface CartItemService {

    int add(CartItemDTO cartItemDTO);

    void edit(CartItemDTO cartItemDTO);

    void delete(CartItemDTO cartItemDTO);

    CartItemDTO getById(int id);

    List<CartItemDTO> getAll();
}

package com.trongdung.website.service;

import com.trongdung.website.model.CartDTO;

import java.util.List;

public interface CartService {

    int add(CartDTO cartDTO);

    void edit(CartDTO cartDTO);

    void delete(CartDTO cartDTO);

    CartDTO getById(int id);

    List<CartDTO> getAll();
}

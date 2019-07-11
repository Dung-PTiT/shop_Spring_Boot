package com.trongdung.website.service;

import com.trongdung.website.model.ProductDTO;

import java.util.List;

public interface ProductService {
    int add(ProductDTO productDTO);

    void edit(ProductDTO productDTO);

    void delete(ProductDTO productDTO);

    ProductDTO getById(int id);

    List<ProductDTO> getByName(String name);

    List<ProductDTO> getAll();

}

package com.trongdung.website.service.impl;

import com.trongdung.website.dao.CategoryDAO;
import com.trongdung.website.dao.ProductDAO;
import com.trongdung.website.entity.ProductEntity;
import com.trongdung.website.model.CategoryDTO;
import com.trongdung.website.model.ProductDTO;
import com.trongdung.website.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public int add(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDTO.getName());
        productEntity.setQuantity(productDTO.getQuantity());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setProductFileName(productDTO.getProductFileName());
        productEntity.setCategoryEntity(categoryDAO.getById(productDTO.getCategoryDTO().getId()));
        return productDAO.add(productEntity);
    }

    @Override
    public void edit(ProductDTO productDTO) {
        ProductEntity productEntity = productDAO.getById(productDTO.getId());
        productEntity.setName(productDTO.getName());
        productEntity.setQuantity(productDTO.getQuantity());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setProductFileName(productDTO.getProductFileName());
        productEntity.setCategoryEntity(categoryDAO.getById(productDTO.getCategoryDTO().getId()));
        productDAO.edit(productEntity);
    }

    @Override
    public void delete(ProductDTO productDTO) {
        ProductEntity productEntity = productDAO.getById(productDTO.getId());
        productDAO.delete(productEntity);
    }

    @Override
    public ProductDTO getById(int id) {
        ProductEntity productEntity = productDAO.getById(id);
        if (productEntity != null) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(productEntity.getId());
            productDTO.setName(productEntity.getName());
            productDTO.setQuantity(productEntity.getQuantity());
            productDTO.setPrice(productEntity.getPrice());
            productDTO.setDescription(productEntity.getDescription());
            productDTO.setProductFileName(productEntity.getProductFileName());
            productDTO.setCategoryDTO(new CategoryDTO(productEntity.getCategoryEntity().getId(), productEntity.getCategoryEntity().getName()));
            return productDTO;
        }
        return null;
    }

    @Override
    public List<ProductDTO> getByName(String name) {
        try {
            List<ProductEntity> productEntities = productDAO.getByName(name);
            List<ProductDTO> productDTOS = new ArrayList<>();
            productEntities.forEach(productEntity -> {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setId(productEntity.getId());
                productDTO.setName(productEntity.getName());
                productDTO.setQuantity(productEntity.getQuantity());
                productDTO.setPrice(productEntity.getPrice());
                productDTO.setDescription(productEntity.getDescription());
                productDTO.setProductFileName(productEntity.getProductFileName());
                productDTO.setCategoryDTO(new CategoryDTO(productEntity.getCategoryEntity().getId(), productEntity.getCategoryEntity().getName()));
                productDTOS.add(productDTO);
            });
            return productDTOS;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ProductDTO> getAll() {
        List<ProductEntity> productEntities = productDAO.getAll();
        List<ProductDTO> listProduct = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(productEntity.getId());
            productDTO.setName(productEntity.getName());
            productDTO.setQuantity(productEntity.getQuantity());
            productDTO.setPrice(productEntity.getPrice());
            productDTO.setDescription(productEntity.getDescription());
            productDTO.setProductFileName(productEntity.getProductFileName());
            productDTO.setCategoryDTO(new CategoryDTO(productEntity.getCategoryEntity().getId(), productEntity.getCategoryEntity().getName()));
            listProduct.add(productDTO);
        }
        return listProduct;
    }
}

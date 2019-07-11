package com.trongdung.website.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String description;
    private String productFileName;
    private MultipartFile productFile;
    private CategoryDTO categoryDTO;

    public ProductDTO() {
    }

    public ProductDTO(int id, String name, int quantity, double price, String description, String productFileName, MultipartFile productFile, CategoryDTO categoryDTO) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.productFileName = productFileName;
        this.productFile = productFile;
        this.categoryDTO = categoryDTO;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public MultipartFile getProductFile() {
        return productFile;
    }

    public void setProductFile(MultipartFile productFile) {
        this.productFile = productFile;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductFileName() {
        return productFileName;
    }

    public void setProductFileName(String productFileName) {
        this.productFileName = productFileName;
    }
}

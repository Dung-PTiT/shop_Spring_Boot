package com.trongdung.website.entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "product_file_name")
    private String productFileName;

    @ManyToOne(fetch = FetchType.EAGER)//mặc định vs ManyToOne
    @JoinColumn(name = "cate_id")
    private CategoryEntity categoryEntity;

    public ProductEntity() {
    }

    public ProductEntity(String name, int quantity, double price, String description, String productFileName, CategoryEntity categoryEntity) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.productFileName = productFileName;
        this.categoryEntity = categoryEntity;
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

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
}

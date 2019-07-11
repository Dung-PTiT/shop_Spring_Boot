package com.trongdung.website.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_item")
public class CartItemEntity implements Serializable{
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(name = "buy_quantity", nullable = false)
    private int buyQuantity;

    @Column(name = "sell_price", nullable = false)
    private double sellPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id",nullable = false)
    private CartEntity cart;

    public CartItemEntity() {
    }

    public CartItemEntity(ProductEntity product, int buyQuantity, double sellPrice, CartEntity cart) {
        this.product = product;
        this.buyQuantity = buyQuantity;
        this.sellPrice = sellPrice;
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(int buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }
}

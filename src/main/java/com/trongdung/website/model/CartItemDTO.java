package com.trongdung.website.model;

public class CartItemDTO {
    private int id;
    private ProductDTO productDTO;
    private int buyQuantity;
    private double sellPrice;
    private CartDTO cartDTO;

    public CartItemDTO() {
    }

    public CartItemDTO(int id, ProductDTO productDTO, int buyQuantity, double sellPrice, CartDTO cartDTO) {
        this.id = id;
        this.productDTO = productDTO;
        this.buyQuantity = buyQuantity;
        this.sellPrice = sellPrice;
        this.cartDTO = cartDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
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

    public CartDTO getCartDTO() {
        return cartDTO;
    }

    public void setCartDTO(CartDTO cartDTO) {
        this.cartDTO = cartDTO;
    }
}

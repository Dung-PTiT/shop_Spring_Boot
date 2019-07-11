package com.trongdung.website.model;

import java.util.Date;

public class CartDTO {
    private int id;
    private UserDTO buyer;
    private Date buyDate;

    public CartDTO() {
    }

    public CartDTO(int id, UserDTO buyer, Date buyDate) {
        this.id = id;
        this.buyer = buyer;
        this.buyDate = buyDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO getBuyer() {
        return buyer;
    }

    public void setBuyer(UserDTO buyer) {
        this.buyer = buyer;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

}

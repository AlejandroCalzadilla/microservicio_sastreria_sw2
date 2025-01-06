package com.accounts.notepurchase.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class DetailNote {
    @Field
    private int quantity;
    private float price;
    private String rawMaterialId;

    // Getters y setters

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getRawMaterialId() {
        return rawMaterialId;
    }

    public void setRawMaterialId(String rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }
}

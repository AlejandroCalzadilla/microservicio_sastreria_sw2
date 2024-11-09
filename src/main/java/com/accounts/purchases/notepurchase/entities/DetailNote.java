package com.accounts.purchases.notepurchase.entities;

import org.springframework.data.annotation.Id;

public class DetailNote {
    @Id
    private String id;
    private int quantity;
    private float price;
    private String rawMaterialId;

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

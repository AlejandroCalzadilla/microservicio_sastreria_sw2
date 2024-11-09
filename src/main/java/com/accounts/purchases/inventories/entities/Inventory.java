package com.accounts.purchases.inventories.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventory")
public class Inventory {

    @Id
    private String id;
    private int quantity;
    private String rawMaterialId;
    private String storeId;


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

    public String getRawMaterialId() {
        return rawMaterialId;
    }

    public void setRawMaterialId(String rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String store) {
        this.storeId = store;
    }


}

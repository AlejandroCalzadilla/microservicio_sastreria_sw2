package com.accounts.notepurchase.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "notes")
public class Note {
    @Id
    private String id;
    private String date;
    private String type;
    private Float totalAmount;
    private List<DetailNote> detailNotes;
    private String storeId;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<DetailNote> getDetailNotes() {
        return detailNotes;
    }

    public void setDetailNotes(List<DetailNote> detailNotes) {
        this.detailNotes = detailNotes;
    }




    // Getters and Setters
}

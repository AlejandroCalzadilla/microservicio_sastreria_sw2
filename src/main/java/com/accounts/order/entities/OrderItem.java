package com.accounts.order.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class OrderItem {
    @Field
    private String garmentId; // Relación con Garment
    private Integer quantity;
    private Double price;
    private String status; // Ej: pendiente, en_produccion, completado, entregado
    private String measurementData;
    // Getters y Setters


    public String getMeasurementData() {
        return measurementData;
    }

    public void setMeasurementData(String measurementData) {
        this.measurementData = measurementData;
    }

    public String getGarmentId() {
        return garmentId;
    }

    public void setGarmentId(String garmentId) {
        this.garmentId = garmentId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}

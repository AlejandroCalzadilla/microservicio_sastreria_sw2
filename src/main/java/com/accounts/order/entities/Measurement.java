package com.accounts.order.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class Measurement {
    private String id;
    private String orderItemId; // Relación con OrderItem
    private String measurementData; // Usar JSON o String para las medidas (ej. pecho, cintura)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getMeasurementData() {
        return measurementData;
    }

    public void setMeasurementData(String measurementData) {
        this.measurementData = measurementData;
    }

    // Getters y Setters
}

package com.accounts.pedido.modelos;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "orders")
public class Pedido {
    @Id
    private String id;
    private String customerId; // Relación con Customer
    private String orderDate;
    private String status; // Ej: pendiente, en_produccion, completado, entregado
    private Double totalPrice;
    private String creadoen;
    private String actualizadoen;
    private List<PedidoItem> orderItems;

    public List<PedidoItem> getOrderItems() {
        return orderItems;
    }


    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", creadoen='" + creadoen + '\'' +
                ", actualizadoen='" + actualizadoen + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }

    public String getCreadoen() {
        return creadoen;
    }

    public void setCreadoen(String creadoen) {
        this.creadoen = creadoen;
    }

    public String getActualizadoen() {
        return actualizadoen;
    }

    public void setActualizadoen(String actualizadoen) {
        this.actualizadoen = actualizadoen;
    }

    public void setOrderItems(List<PedidoItem> orderItems) {
        this.orderItems = orderItems;
    }

    // Relación con OrderItem
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


    // Getters y Setters
}


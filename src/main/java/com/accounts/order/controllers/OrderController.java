package com.accounts.order.controllers;

import com.accounts.order.entities.Order;
import com.accounts.order.entities.OrderItem;
import com.accounts.order.repositories.OrderItemRepository;
import com.accounts.order.repositories.OrderRepository;
import com.accounts.utils.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @QueryMapping
    public Order getOrder(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    @QueryMapping
    public PaginatedResponse<Order> getOrders(@Argument int limit, @Argument int offset) {
        List<Order> allOrders = orderRepository.findAll();
        int totalCount = allOrders.size();
        int toIndex = Math.min(offset + limit, totalCount);
        List<Order> orders = allOrders.subList(offset, toIndex);
        return new PaginatedResponse<>(orders, totalCount);
    }

    @MutationMapping
    public Order createOrder(@Argument String customerId,
                             @Argument String orderDate,
                             @Argument String status,
                             @Argument Double totalPrice,
                             @Argument List<OrderItem> items) {
        Order order = new Order();
        order.setCustomerId(customerId);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            order.setOrderDate(dateFormat.parse(orderDate));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + orderDate, e);
        }
        order.setStatus(status);
        order.setTotalPrice(totalPrice);
        order.setOrderItems(items);
        Order savedOrder = orderRepository.save(order);

        return savedOrder;
    }

    @MutationMapping
    public Order updateOrder(String id, String customerId, String orderDate, String status, Double totalPrice, List<OrderItem> items) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));

        if (customerId != null) order.setCustomerId(customerId);
        if (orderDate != null) order.setOrderDate(new Date(orderDate));
        if (status != null) order.setStatus(status);
        if (totalPrice != null) order.setTotalPrice(totalPrice);
        if(items != null) order.setOrderItems(items);
        Order updatedOrder = orderRepository.save(order);



        return updatedOrder;
    }

    @MutationMapping
    public boolean deleteOrder(String id) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            orderRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Order not found with ID: " + id);
        }
    }

}

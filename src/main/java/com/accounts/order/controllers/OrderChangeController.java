package com.accounts.order.controllers;

import com.accounts.order.entities.OrderChange;
import com.accounts.order.repositories.OrderChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Optional;


@Controller
public class OrderChangeController {

    @Autowired
    private OrderChangeRepository orderChangeRepository;

    @QueryMapping
    public OrderChange getOrderChange(String id) {
        return orderChangeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderChange not found with ID: " + id));
    }

    @MutationMapping
    public OrderChange createOrderChange(String orderItemId, String changeDate, String description, String requestedBy, String status) {
        OrderChange orderChange = new OrderChange();
        orderChange.setOrderItemId(orderItemId);
        orderChange.setChangeDate(new Date(changeDate));
        orderChange.setDescription(description);
        orderChange.setRequestedBy(requestedBy);
        orderChange.setStatus(status);
        return orderChangeRepository.save(orderChange);
    }

    @MutationMapping(name = "updateOrderChange")
    public OrderChange updateOrderChange(String id, String orderItemId, String changeDate, String description, String requestedBy, String status) {
        OrderChange orderChange = orderChangeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderChange not found with ID: " + id));

        if (orderItemId != null) orderChange.setOrderItemId(orderItemId);
        if (changeDate != null) orderChange.setChangeDate(new Date(changeDate));
        if (description != null) orderChange.setDescription(description);
        if (requestedBy != null) orderChange.setRequestedBy(requestedBy);
        if (status != null) orderChange.setStatus(status);

        return orderChangeRepository.save(orderChange);
    }

    @MutationMapping
    public boolean deleteOrderChange(String id) {
        Optional<OrderChange> orderChange = orderChangeRepository.findById(id);

        if (orderChange.isPresent()) {
            orderChangeRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("OrderChange not found with ID: " + id);
        }
    }
}

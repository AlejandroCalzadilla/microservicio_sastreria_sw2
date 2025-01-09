package com.accounts.order.repositories;

import com.accounts.pedido.modelos.OrderChange;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderChangeRepository extends MongoRepository<OrderChange, String> {
}

package com.accounts.pedido.repositorios;

import com.accounts.pedido.modelos.PedidoItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoItemRepository extends MongoRepository<PedidoItem, String> {
}

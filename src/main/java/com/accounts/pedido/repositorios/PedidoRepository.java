package com.accounts.pedido.repositorios;

import com.accounts.pedido.modelos.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PedidoRepository extends MongoRepository<Pedido, String> {


    List<Pedido> findByCustomerId(String customerId);
}

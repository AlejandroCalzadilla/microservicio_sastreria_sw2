package com.accounts.pedido.servicios;

import com.accounts.pedido.modelos.Pedido;
import com.accounts.utils.PaginatedResponse;

import java.util.List;

public interface PedidoServicio {

    public PaginatedResponse<Pedido> findAll(int limit, int offset);
    public Pedido findById(String id);
    public Pedido create(Pedido pedido);
    public Pedido update(String id, Pedido pedido);
    public String delete(String id);


}

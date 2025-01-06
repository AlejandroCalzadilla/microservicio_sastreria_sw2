package com.accounts.pedido.servicios;

import com.accounts.microservicio.Peticiones;
import com.accounts.pedido.modelos.Pedido;
import com.accounts.pedido.repositorios.PedidoRepository;
import com.accounts.utils.PaginatedResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;



@Service
public class PedidoServicioImpl implements PedidoServicio {



    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private Peticiones peticiones;


    public PaginatedResponse<Pedido> findAll(int limit, int offset) {
        List<Pedido> allOrders = pedidoRepository.findAll();
        int totalCount = allOrders.size();
        int toIndex = Math.min(offset + limit, totalCount);
        List<Pedido> orders = allOrders.subList(offset, toIndex);
        return new PaginatedResponse<>(orders, totalCount);

    }

    public Pedido findById(String id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));

    }

    public Pedido create(Pedido pedido) {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        pedido.setCreadoen(currentTime);
        pedido.setActualizadoen(currentTime);
        return pedidoRepository.save(pedido);
    }



    public Pedido update(String id, Pedido pedido) {
        Pedido pedido1 = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order con id " + id + " no encontrada"));
        if ("entregado".equalsIgnoreCase(pedido1.getStatus())) {
            throw new RuntimeException("No se puede actualizar un pedido con estado 'entregado'");
        }

        if (pedido.getOrderDate() != null) pedido1.setOrderDate(pedido.getOrderDate());
        if (pedido.getStatus() != null) pedido1.setStatus(pedido.getStatus());
        if (pedido.getTotalPrice() != null) pedido1.setTotalPrice(pedido.getTotalPrice());
        if (pedido.getOrderItems() != null) pedido1.setOrderItems(pedido.getOrderItems());
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        pedido1.setActualizadoen(currentTime);

        if ("entregado".equalsIgnoreCase(pedido.getStatus())) {
            Pedido pedido2 = new Pedido();
            pedido2.setId(pedido1.getId());
            pedido2.setCustomerId(pedido1.getCustomerId());
            pedido2.setOrderDate(pedido1.getOrderDate());
            pedido2.setStatus(pedido.getStatus());
            pedido2.setTotalPrice(pedido1.getTotalPrice());
            pedido2.setOrderItems(pedido1.getOrderItems());
            pedido2.setActualizadoen(currentTime);
             peticiones.sendPedidoToExternalService(pedido2);
        }


        return pedidoRepository.save(pedido1);
    }

    public String delete(String id) {
        pedidoRepository.deleteById(id);
        return "Pedido con id "+id+" eliminado";

    }



}

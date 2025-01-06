package com.accounts.pedido;

import com.accounts.pedido.modelos.Pedido;
import com.accounts.pedido.modelos.PedidoItem;
import com.accounts.pedido.repositorios.PedidoItemRepository;
import com.accounts.pedido.repositorios.PedidoRepository;
import com.accounts.pedido.servicios.PedidoServicioImpl;
import com.accounts.utils.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class PedidoController {

    @Autowired
    private PedidoRepository orderRepository;

    @Autowired
    private PedidoItemRepository orderItemRepository;

    @Autowired
    private PedidoServicioImpl pedidoServicio;

    @QueryMapping
    public Pedido findByIdPedido(String id) {
        return pedidoServicio.findById(id);
    }

    @QueryMapping
    public PaginatedResponse<Pedido> findAllPedido(@Argument int limit, @Argument int offset) {
       return pedidoServicio.findAll(limit, offset);
    }


    @MutationMapping
    public Pedido createPedido(@Argument  Pedido pedido) {
        return pedidoServicio.create(pedido);
    }



    /*
    @MutationMapping
    public Pedido createOrder(@Argument String customerId,
                              @Argument String orderDate,
                              @Argument String status,
                              @Argument Double totalPrice,
                              @Argument List<PedidoItem> items) {



        //System.out.println(items + "llegan los items");


        Pedido order = new Pedido();
        order.setCustomerId(customerId);
        order.setOrderDate(orderDate);
        order.setStatus(status);
        order.setTotalPrice(totalPrice);
        order.setOrderItems(items);
        Pedido savedOrder = orderRepository.save(order);

        return savedOrder;
    }*/


    @QueryMapping
    public List<Pedido> getOrdersByCustomer(@Argument String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }




    @MutationMapping
    public Pedido updatePedido(@Argument  String id,@Argument  Pedido pedido) {

        return pedidoServicio.update(id,pedido);
         //System.out.println("id: "+id);

       /*
        Pedido order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        if (customerId != null) order.setCustomerId(customerId);
        if (orderDate != null) order.setOrderDate(orderDate);
        if (status != null) order.setStatus(status);
        if (totalPrice != null) order.setTotalPrice(totalPrice);
        if(items != null) order.setOrderItems(items);
        Pedido updatedOrder = orderRepository.save(order);
        return updatedOrder;*/
    }

    @MutationMapping
    public String deletePedido(@Argument String id) {
       return pedidoServicio.delete(id);
    }

}

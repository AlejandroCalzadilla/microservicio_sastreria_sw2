package com.accounts.seeders;

import com.accounts.pedido.modelos.Pedido;
import com.accounts.pedido.modelos.OrderChange;
import com.accounts.order.repositories.OrderChangeRepository;
import com.accounts.pedido.repositorios.PedidoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;



@Component
public class OrderChangeSeeder {


    @Autowired
    private PedidoRepository orderRepository;
    @Autowired
    private OrderChangeRepository orderChangeRepository;

    public  void seed(){

        Faker faker = new Faker();


        List<Pedido> orders = orderRepository.findAll();

        for (int i = 0; i < 500; i++) {
            OrderChange orderChange = new OrderChange();
            orderChange.setOrderId(orders.get(faker.number().numberBetween(0, orders.size())).getId());
            orderChange.setChangeDate(faker.date().past(30, TimeUnit.DAYS));
            orderChange.setDescription(faker.lorem().sentence());
            orderChange.setRequestedBy(faker.options().option("cliente", "sastrería"));
            orderChange.setStatus(faker.options().option("pendiente", "completado"));
            orderChangeRepository.save(orderChange);
        }

    }


}

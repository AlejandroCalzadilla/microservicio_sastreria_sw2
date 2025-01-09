package com.accounts.seeders;

import com.accounts.pedido.repositorios.PedidoRepository;
import com.accounts.order.repositories.OrderChangeRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {

    @Autowired
    private PedidoRepository orderRepository;

    @Autowired
    private OrderChangeRepository orderChangeRepository;


    @Autowired
    private RawMaterialSeeder rawMaterialSeeder;

    @Autowired
    private StoreSeeder storeSeeder;

     @Autowired
     private CustomerSeeder customerSeeder;

     @Autowired
     private  GarmentSeeder garmentSeeder;


     @Autowired
     private  OrderSeeder orderSeeder;


     @Autowired
     private NotePurchaseSeeder notePurchaseSeeder;

     @Autowired
     private  OrderChangeSeeder orderChangeSeeder;



    public void seed() throws Exception {
        Faker faker = new Faker();

        rawMaterialSeeder.seed();
        storeSeeder.seed();
        customerSeeder.seed();
        garmentSeeder.seed();
        orderSeeder.seed();
        notePurchaseSeeder.seed();
        orderChangeSeeder.seed();




        // Create data for "Order" with "OrderItem"

    }
}
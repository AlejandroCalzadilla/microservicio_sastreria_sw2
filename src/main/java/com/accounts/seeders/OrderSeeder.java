package com.accounts.seeders;

import com.accounts.order.entities.Garment;
import com.accounts.pedido.modelos.Pedido;
import com.accounts.pedido.modelos.PedidoItem;
import com.accounts.order.repositories.GarmentRepository;
import com.accounts.pedido.repositorios.PedidoRepository;
import com.accounts.cliente.modelos.Cliente;
import com.accounts.cliente.repositorios.ClienteRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class OrderSeeder {

    @Autowired
    private PedidoRepository orderRepository;

    @Autowired
    private ClienteRepository customerRepository;

    @Autowired
    private GarmentRepository garmentRepository;

    private List<String> imageBase64List = new ArrayList<>();

    public void seed() throws IOException {
        // Cargar todas las imágenes del directorio
        loadImages();

        Faker faker = new Faker();
        List<Cliente> customers = customerRepository.findAll();
        List<Garment> garments = garmentRepository.findAll();
        Random random = new Random();

        for (int i = 0; i < 5000; i++) {
            Pedido order = new Pedido();
            order.setCustomerId(customers.get(faker.number().numberBetween(0, customers.size())).getId());
            order.setOrderDate(String.valueOf(faker.date().between(
                    Date.from(LocalDate.of(2022, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(LocalDate.of(2024, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant())
            )));
            order.setStatus(faker.options().option("pendiente", "en_produccion", "completado", "entregado"));
            order.setTotalPrice(faker.number().randomDouble(2, 100, 1000));

            List<PedidoItem> orderItems = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                PedidoItem orderItem = new PedidoItem();
                orderItem.setQuantity(faker.number().numberBetween(1, 10));
                orderItem.setPrice(faker.number().randomDouble(2, 10, 100));
                orderItem.setDescripcion(faker.lorem().sentence());

                // Seleccionar aleatoriamente una imagen y establecerla como cadena base64
                String imageBase64 = imageBase64List.get(random.nextInt(imageBase64List.size()));
                System.out.println(imageBase64+ "avr que esta pasando llegan las imagenes o neñ");
                orderItem.setImage(imageBase64);

                orderItems.add(orderItem);
            }
            order.setOrderItems(orderItems);
            orderRepository.save(order);
        }
    }

    private void loadImages() throws IOException {
        File imageDir = new File("src/main/java/com/accounts/imagenes");
        File[] imageFiles = imageDir.listFiles();

        if (imageFiles != null) {
            for (File imageFile : imageFiles) {
                if (imageFile.isFile() && !imageFile.getName().equals("FileStorageService.java")) {
                    byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
                    String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
                    imageBase64List.add(imageBase64);
                }
            }
        }
    }
}
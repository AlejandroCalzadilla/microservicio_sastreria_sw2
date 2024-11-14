package com.accounts.seeder;

import com.accounts.order.entities.Garment;
import com.accounts.purchases.customers.entities.Customer;
import com.accounts.purchases.customers.entities.Telephone;
import com.accounts.purchases.customers.repositories.CustomerRepository;
import com.accounts.purchases.inventories.entities.Inventory;
import com.accounts.purchases.inventories.repositories.InventoryRepository;
import com.accounts.purchases.notepurchase.entities.DetailNote;
import com.accounts.purchases.notepurchase.entities.Note;
import com.accounts.purchases.notepurchase.repositories.NoteRepository;
import com.accounts.purchases.rawmaterial.entities.RawMaterial;
import com.accounts.purchases.rawmaterial.repositories.RawMaterialRepository;
import com.accounts.purchases.stores.enities.Store;
import com.accounts.purchases.stores.repositories.StoreRepository;
import com.accounts.order.entities.Order;
import com.accounts.order.entities.OrderItem;
import com.accounts.order.entities.OrderChange;
import com.accounts.order.repositories.OrderRepository;
import com.accounts.order.repositories.OrderChangeRepository;
import com.accounts.order.repositories.GarmentRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderChangeRepository orderChangeRepository;

    @Autowired
    private GarmentRepository garmentRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Create data for "Store"
        for (int i = 0; i < 5; i++) {
            Store store = new Store();
            store.setName(faker.company().name());
            store.setAddress(faker.address().streetAddress());
            storeRepository.save(store);
        }

        // Create data for "RawMaterial"
        for (int i = 0; i < 500; i++) {
            RawMaterial rawMaterial = new RawMaterial();
            rawMaterial.setName(faker.commerce().productName());
            rawMaterial.setUnit(faker.options().option("kg", "m", "liters", "pcs"));
            rawMaterialRepository.save(rawMaterial);
        }

        // Create data for "Customer" with "Telephone"
        for (int i = 0; i < 300; i++) {
            Customer customer = new Customer();
            customer.setFirstName(faker.name().firstName());
            customer.setLastName(faker.name().lastName());
            customer.setCi(faker.idNumber().valid());
            customer.setBirthDate(faker.date().birthday().toString());
            customer.setSex(faker.options().option("M", "F"));

            List<Telephone> telephones = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                Telephone telephone = new Telephone();
                telephone.setNumber(faker.phoneNumber().cellPhone());
                telephone.setType(faker.options().option("Home", "Work", "Mobile"));
                telephones.add(telephone);
            }
            customer.setTelephones(telephones);
            customerRepository.save(customer);
        }

        // Create data for "Note" with "DetailNote" and update inventory
        List<Store> stores = storeRepository.findAll();
        List<RawMaterial> rawMaterials = rawMaterialRepository.findAll();

        for (int i = 0; i < 10550; i++) {
            Note note = new Note();
            note.setDate(faker.date().between(
                    Date.from(LocalDate.of(2022, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(LocalDate.of(2024, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant())
            ).toString());

            note.setType(faker.options().option("Type1", "Type2", "Type3"));
            note.setTotalAmount((float) faker.number().randomDouble(2, 100, 1000));
            note.setStoreId(stores.get(faker.number().numberBetween(0, stores.size())).getId());

            List<DetailNote> detailNotes = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                DetailNote detailNote = new DetailNote();
                detailNote.setQuantity(faker.number().numberBetween(1, 10));
                detailNote.setPrice((float) faker.number().randomDouble(2, 10, 100));
                detailNote.setRawMaterialId(rawMaterials.get(faker.number().numberBetween(0, rawMaterials.size())).getId());
                detailNotes.add(detailNote);
            }
            note.setDetailNotes(detailNotes);
            Note savedNote = noteRepository.save(note);

            // Update inventory for each detail note
            for (DetailNote detailNote : detailNotes) {
                Inventory inventory = new Inventory();
                inventory.setQuantity(detailNote.getQuantity());
                inventory.setRawMaterialId(detailNote.getRawMaterialId());
                inventory.setStoreId(note.getStoreId());
                inventoryRepository.save(inventory);
            }
        }

        // Create data for "Garment"
        for (int i = 0; i < 100; i++) {
            Garment garment = new Garment();
            garment.setName(faker.commerce().productName());
            garment.setDescription(faker.lorem().sentence());
            garment.setBasePrice(faker.number().randomDouble(2, 50, 500));
            garment.setImage(faker.internet().image());
            garmentRepository.save(garment);
        }

        // Create data for "Order" with "OrderItem"
        List<Customer> customers = customerRepository.findAll();
        List<Garment> garments = garmentRepository.findAll();

        for (int i = 0; i < 5000; i++) {
            Order order = new Order();
            order.setCustomerId(customers.get(faker.number().numberBetween(0, customers.size())).getId());
            order.setOrderDate(faker.date().between(
                    Date.from(LocalDate.of(2022, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(LocalDate.of(2024, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant())
            ));
            order.setStatus(faker.options().option("pendiente", "en_produccion", "completado", "entregado"));
            order.setTotalPrice(faker.number().randomDouble(2, 100, 1000));

            List<OrderItem> orderItems = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setGarmentId(garments.get(faker.number().numberBetween(0, garments.size())).getId());
                orderItem.setQuantity(faker.number().numberBetween(1, 10));
                orderItem.setPrice(faker.number().randomDouble(2, 10, 100));
                orderItem.setStatus(faker.options().option("pendiente", "en_produccion", "completado", "entregado"));
                orderItem.setMeasurementData(faker.lorem().sentence());
                orderItems.add(orderItem);
            }
            order.setOrderItems(orderItems);
            orderRepository.save(order);
        }

        // Create data for "OrderChange"
        List<Order> orders = orderRepository.findAll();

        for (int i = 0; i < 500; i++) {
            OrderChange orderChange = new OrderChange();
            orderChange.setOrderId(orders.get(faker.number().numberBetween(0, orders.size())).getId());
            orderChange.setChangeDate(faker.date().past(30, TimeUnit.DAYS));
            orderChange.setDescription(faker.lorem().sentence());
            orderChange.setRequestedBy(faker.options().option("cliente", "sastrería"));
            orderChange.setStatus(faker.options().option("pendiente", "completado"));
            orderChangeRepository.save(orderChange);
        }

        System.out.println("Test data loaded into the database.");
    }
}
package com.accounts.seeder;


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
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Crear datos para "Store"
        for (int i = 0; i < 5; i++) {
            Store store = new Store();
            store.setName(faker.company().name());
            store.setAddress(faker.address().streetAddress());
            storeRepository.save(store);
        }

        // Crear datos para "RawMaterial"
        for (int i = 0; i < 500; i++) {
            RawMaterial rawMaterial = new RawMaterial();
            rawMaterial.setName(faker.commerce().productName());
            rawMaterial.setUnit(faker.options().option("kg", "m", "liters", "pcs"));
            rawMaterialRepository.save(rawMaterial);
        }

        // Crear datos para "Inventory"


        List<Store> stores = storeRepository.findAll();
        List<RawMaterial> rawMaterials = rawMaterialRepository.findAll();
       /*
        for (int i = 0; i < 5; i++) {
            Inventory inventory = new Inventory();
            inventory.setQuantity(faker.number().numberBetween(10, 100));
            inventory.setRawMaterialId(rawMaterials.get(faker.number().numberBetween(0, rawMaterials.size())).getId());
            inventory.setStoreId(stores.get(faker.number().numberBetween(0, stores.size())).getId());
            inventoryRepository.save(inventory);
        }*/

        // Crear datos para "Customer" con "Telephone"
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

        // Crear datos para "Note" con "DetailNote" y actualizar el inventario
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
                detailNote.setId(UUID.randomUUID().toString());
                detailNote.setQuantity(faker.number().numberBetween(1, 10));
                detailNote.setPrice((float) faker.number().randomDouble(2, 10, 100));
                detailNote.setRawMaterialId(rawMaterials.get(faker.number().numberBetween(0, rawMaterials.size())).getId());
                detailNotes.add(detailNote);
            }
            note.setDetailNotes(detailNotes);
            Note savedNote = noteRepository.save(note);

            // Actualizar el inventario para cada detail note
            for (DetailNote detailNote : detailNotes) {
                Inventory inventory = new Inventory();
                inventory.setQuantity(detailNote.getQuantity());
                inventory.setRawMaterialId(detailNote.getRawMaterialId());
                inventory.setStoreId(note.getStoreId());
                inventoryRepository.save(inventory);
            }
        }

        System.out.println("Datos de prueba cargados en la base de datos.");
    }
}
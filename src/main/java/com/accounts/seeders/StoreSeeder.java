package com.accounts.seeders;

import com.accounts.almacen.enities.Store;
import com.accounts.almacen.repositories.StoreRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreSeeder {

    @Autowired
    private StoreRepository storeRepository;


    public void seed() {
        Faker faker = new Faker();
        for (int i = 0; i < 2; i++) {
            Store store = new Store();
            store.setName(faker.company().name());
            store.setAddress(faker.address().streetAddress());
            storeRepository.save(store);
        }
        // Add your store seeding logic here
    }

}

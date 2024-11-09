package com.accounts.purchases.stores.repositories;

import com.accounts.purchases.stores.enities.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<Store, String> {
}

package com.accounts.almacen.repositories;

import com.accounts.almacen.enities.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<Store, String> {
}

package com.accounts.purchases.inventories.repositories;

import com.accounts.purchases.inventories.entities.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository  extends MongoRepository<Inventory,String> {
}

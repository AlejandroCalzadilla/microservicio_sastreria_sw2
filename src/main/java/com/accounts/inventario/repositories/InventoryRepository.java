package com.accounts.inventario.repositories;

import com.accounts.inventario.entities.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository  extends MongoRepository<Inventory,String> {
}

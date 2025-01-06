package com.accounts.inventario;

import com.accounts.inventario.entities.Inventory;
import com.accounts.inventario.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> findAllInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory findInventoryById(String id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public Inventory createInventory(int quantity, String rawMaterialId, String storeId) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(quantity);
        inventory.setRawMaterialId(rawMaterialId);
        inventory.setStoreId(storeId);
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(String id, int quantity, String rawMaterialId, String storeId) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        if (optionalInventory.isPresent()) {
            Inventory inventory = optionalInventory.get();
            inventory.setQuantity(quantity);
            inventory.setRawMaterialId(rawMaterialId);
            inventory.setStoreId(storeId);
            return inventoryRepository.save(inventory);
        }
        return null;
    }

    public void deleteInventory(String id) {
        inventoryRepository.deleteById(id);
    }
}

package com.accounts.inventario;


import com.accounts.inventario.entities.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @QueryMapping
    public List<Inventory> findAllInventories() {
        return inventoryService.findAllInventories();
    }

    @QueryMapping
    public Inventory findInventoryById(@Argument String id) {
        return inventoryService.findInventoryById(id);
    }

    @MutationMapping
    public Inventory createInventory(@Argument int quantity, @Argument String rawMaterialId, @Argument String storeId) {
        return inventoryService.createInventory(quantity, rawMaterialId, storeId);
    }

    @MutationMapping
    public Inventory updateInventory(@Argument String id, @Argument int quantity, @Argument String rawMaterialId, @Argument String storeId) {
        return inventoryService.updateInventory(id, quantity, rawMaterialId, storeId);
    }

    @MutationMapping
    public String deleteInventory(@Argument String id) {
        inventoryService.deleteInventory(id);
        return "Inventory deleted successfully";
    }

}

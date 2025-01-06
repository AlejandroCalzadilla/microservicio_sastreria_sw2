package com.accounts.seeders;


import com.accounts.inventario.entities.Inventory;
import com.accounts.inventario.repositories.InventoryRepository;
import com.accounts.notepurchase.entities.DetailNote;
import com.accounts.notepurchase.entities.Note;
import com.accounts.notepurchase.repositories.NoteRepository;
import com.accounts.materiaprima.models.RawMaterial;
import com.accounts.materiaprima.repositories.RawMaterialRepository;
import com.accounts.almacen.enities.Store;
import com.accounts.almacen.repositories.StoreRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class NotePurchaseSeeder {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public void seed() {
        // Add your note purchase seeding logic here
        Faker faker = new Faker();

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



    }



}

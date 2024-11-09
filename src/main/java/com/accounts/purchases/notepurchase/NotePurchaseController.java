package com.accounts.purchases.notepurchase;

import com.accounts.purchases.inventories.InventoryService;
import com.accounts.purchases.inventories.entities.Inventory;
import com.accounts.purchases.inventories.repositories.InventoryRepository;
import com.accounts.purchases.notepurchase.entities.DetailNote;
import com.accounts.purchases.notepurchase.entities.Note;
import com.accounts.purchases.notepurchase.repositories.NoteRepository;
import com.accounts.purchases.stores.enities.Store;
import com.accounts.purchases.stores.repositories.StoreRepository;
import com.accounts.utils.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Controller
public class NotePurchaseController {



    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @QueryMapping
    public PaginatedResponse<Note> findAllNotes(@Argument int limit, @Argument int offset) {
        List<Note> allNotes = noteRepository.findAll();
        int total = allNotes.size();

        int end = Math.min(offset + limit, allNotes.size());
        List<Note> paginatedNotes = offset > allNotes.size() ?
                Collections.emptyList() :
                allNotes.subList(offset, end);
        return new PaginatedResponse<>(paginatedNotes, total);
    }


    @QueryMapping
    public Note findNoteById(@Argument String id) {
        return noteRepository.findById(id).orElse(null);
    }






    @MutationMapping
    public Note createNote(@Argument String date, @Argument String type, @Argument Float totalAmount, @Argument List<DetailNote> detailNotes, @Argument String storeId) {
        Optional<Store> optionalStore = storeRepository.findById(storeId);
        if (!optionalStore.isPresent()) {
            throw new IllegalArgumentException("Store not found with id: " + storeId);
        }

        // Crear y guardar la Note
        Note note = new Note();
        note.setDate(date);
        note.setType(type);
        note.setTotalAmount(totalAmount);
        note.setStoreId(storeId);

        // Asegurarse de que cada DetailNote tenga un id
        for (DetailNote detailNote : detailNotes) {
            if (detailNote.getId() == null) {
                detailNote.setId(UUID.randomUUID().toString());
            }
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
        return savedNote;
    }

    @MutationMapping
    public Note updateNote(@Argument String id,@Argument String date, @Argument String type, @Argument Float totalAmount,@Argument List<DetailNote> detailNotes) {
        Optional<Note> optionalCustomer = noteRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Note customer = optionalCustomer.get();
            if (date != null) customer.setDate(date);
            if (type != null) customer.setType(type);
            if (totalAmount != null) customer.setTotalAmount(totalAmount);
            if (detailNotes != null) customer.setDetailNotes(detailNotes);
            return noteRepository.save(customer);
        }
        return null;
    }





    @MutationMapping
    public String deleteNote(@Argument String id) {
        noteRepository.deleteById(id);
        return "Customer deleted successfully";
    }
}

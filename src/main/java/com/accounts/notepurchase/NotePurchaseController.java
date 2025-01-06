package com.accounts.notepurchase;

import com.accounts.inventario.entities.Inventory;
import com.accounts.inventario.repositories.InventoryRepository;
import com.accounts.notepurchase.entities.DetailNote;
import com.accounts.notepurchase.entities.Note;
import com.accounts.notepurchase.repositories.NoteRepository;
import com.accounts.almacen.enities.Store;
import com.accounts.almacen.repositories.StoreRepository;
import com.accounts.utils.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

        Note note = new Note();
        note.setDate(date);
        note.setType(type);
        note.setTotalAmount(totalAmount);
        note.setStoreId(storeId);
        note.setDetailNotes(detailNotes);

        Note savedNote = noteRepository.save(note);

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
    public Note updateNote(@Argument String id, @Argument String date, @Argument String type, @Argument Float totalAmount, @Argument List<DetailNote> detailNotes) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            if (date != null) note.setDate(date);
            if (type != null) note.setType(type);
            if (totalAmount != null) note.setTotalAmount(totalAmount);
            if (detailNotes != null) note.setDetailNotes(detailNotes);
            return noteRepository.save(note);
        }
        return null;
    }

    @MutationMapping
    public String deleteNote(@Argument String id) {
        noteRepository.deleteById(id);
        return "Note deleted successfully";
    }
}
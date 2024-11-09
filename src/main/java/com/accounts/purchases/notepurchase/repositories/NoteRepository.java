package com.accounts.purchases.notepurchase.repositories;

import com.accounts.purchases.notepurchase.entities.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {
}

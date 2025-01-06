package com.accounts.notepurchase.repositories;

import com.accounts.notepurchase.entities.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {
}

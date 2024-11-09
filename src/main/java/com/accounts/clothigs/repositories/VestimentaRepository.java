package com.accounts.clothigs.repositories;

import com.accounts.clothigs.entities.Vestimenta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VestimentaRepository extends MongoRepository<Vestimenta, String> {
}

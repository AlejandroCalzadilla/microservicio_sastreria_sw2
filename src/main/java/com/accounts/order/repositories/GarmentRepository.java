package com.accounts.order.repositories;

import com.accounts.order.entities.Garment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GarmentRepository extends MongoRepository<Garment,String> {
}

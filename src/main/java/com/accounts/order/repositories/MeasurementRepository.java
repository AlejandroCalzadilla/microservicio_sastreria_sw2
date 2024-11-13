package com.accounts.order.repositories;

import com.accounts.order.entities.Measurement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MeasurementRepository extends MongoRepository<Measurement, String> {



}

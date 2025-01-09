package com.accounts.materiaprima.repositories;

import com.accounts.materiaprima.models.RawMaterial;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RawMaterialRepository extends MongoRepository<RawMaterial,String> {
}

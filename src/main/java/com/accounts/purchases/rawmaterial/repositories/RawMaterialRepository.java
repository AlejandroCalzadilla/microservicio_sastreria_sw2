package com.accounts.purchases.rawmaterial.repositories;

import com.accounts.purchases.rawmaterial.entities.RawMaterial;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RawMaterialRepository extends MongoRepository<RawMaterial,String> {
}

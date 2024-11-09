package com.accounts.purchases.customers.repositories;

import com.accounts.purchases.customers.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {
}

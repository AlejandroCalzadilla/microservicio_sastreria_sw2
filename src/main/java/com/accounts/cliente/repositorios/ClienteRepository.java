package com.accounts.cliente.repositorios;

import com.accounts.cliente.modelos.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClienteRepository extends MongoRepository<Cliente,String> {

    Optional<Cliente> findByUserid(String userId);

}

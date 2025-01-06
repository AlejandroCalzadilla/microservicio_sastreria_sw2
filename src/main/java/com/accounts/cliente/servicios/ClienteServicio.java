package com.accounts.cliente.servicios;

import com.accounts.cliente.modelos.Cliente;

import java.util.List;

public interface ClienteServicio {
       public Cliente findById(String id);
        public Cliente create(Cliente cliente);
        public Cliente update(String id, Cliente cliente);
        public String delete(String id);
        public List<Cliente> findAll();
        public Cliente findByUserid(String userId);
}

package com.accounts.cliente.servicios;

import com.accounts.cliente.modelos.Cliente;
import com.accounts.cliente.repositorios.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(String id) {
        return  clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente con id "+id+ " no ecnontrado"));
    }


    @Override
    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }






    @Override
    public Cliente update(String id , Cliente cliente) {
        Cliente cliente1 = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente con id "+id+ " no ecnontrado"));
        if (cliente.getFirstName() != null) cliente1.setFirstName(cliente.getFirstName());
        if (cliente.getLastName() != null) cliente1.setLastName(cliente.getLastName());
        if (cliente.getCi() != null) cliente1.setCi(cliente.getCi());
        if (cliente.getTelephones() != null) cliente1.setTelephones(cliente.getTelephones());
        if (cliente.getBirthDate() != null) cliente1.setBirthDate(cliente.getBirthDate());
        return clienteRepository.save(cliente1);

    }


    public String delete(String id) {
        if (!clienteRepository.existsById(id)) {
            return "Cliente con id " + id + " no encontrado";
        }
        clienteRepository.deleteById(id);
        return "Cliente con id " + id + " eliminado";
    }



    @Override
    public Cliente findByUserid(String userId) {
        return null;
    }


}

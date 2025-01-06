package com.accounts.cliente;

import com.accounts.cliente.modelos.Cliente;
import com.accounts.cliente.modelos.Telephone;
import com.accounts.cliente.repositorios.ClienteRepository;
import com.accounts.cliente.servicios.ClienteServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ClienteController {
    @Autowired
    private ClienteRepository customerRepository;

    @Autowired
    private ClienteServicioImpl clienteServicio;



    @QueryMapping
    public List<Cliente> findAllCliente() {
        return clienteServicio.findAll();
    }


    @QueryMapping(name = "getCustomerByUserId")
    public Cliente getCustomerByUserId(@Argument String userid) {
        return customerRepository.findByUserid(userid).orElse(null);
    }

    @QueryMapping
    public Cliente findClienteById(@Argument String id) {
        return clienteServicio.findById(id);
    }



    @MutationMapping
    public  Cliente createCliente(@Argument Cliente cliente){
        return clienteServicio.create(cliente);
    }



    @MutationMapping
    public String deleteCliente(@Argument String id) {
       return clienteServicio.delete(id);

    }


   @MutationMapping
    public Cliente updateCliente(@Argument String id,@Argument Cliente cliente) {
        return clienteServicio.update(id, cliente);
    }


















    /*
    @MutationMapping
    public Cliente updateCustomer(@Argument String id, @Argument String firstName, @Argument String lastName,
                                  @Argument String ci, @Argument String birthDate, @Argument String sex,
                                  @Argument List<Telephone> telephones) {
        Optional<Cliente> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Cliente customer = optionalCustomer.get();
            if (firstName != null) customer.setFirstName(firstName);
            if (lastName != null) customer.setLastName(lastName);
            if (ci != null) customer.setCi(ci);
            if (birthDate != null) customer.setBirthDate(birthDate);
            if (sex != null) customer.setSex(sex);
            if (telephones != null) customer.setTelephones(telephones);
            return customerRepository.save(customer);
        }
        return null;
    }


     */










    /*
    @MutationMapping
    public Cliente createCustomer(@Argument String firstName, @Argument String lastName, @Argument String ci,
                                  @Argument String birthDate, @Argument String sex, @Argument List<Telephone> telephones) {
        Cliente customer = new Cliente();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setCi(ci);
        customer.setBirthDate(birthDate);
        customer.setSex(sex);
        customer.setTelephones(telephones);
        return customerRepository.save(customer);
    }
    */







}

package com.accounts.seeders;

import com.accounts.cliente.modelos.Cliente;
import com.accounts.cliente.modelos.Telephone;
import com.accounts.cliente.repositorios.ClienteRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerSeeder {

    @Autowired
    private ClienteRepository customerRepository;

    public void seed() {
        Faker faker = new Faker();
        for (int i = 0; i < 300; i++) {
            Cliente customer = new Cliente();
            customer.setFirstName(faker.name().firstName());
            customer.setLastName(faker.name().lastName());
            customer.setCi(faker.idNumber().valid());
            customer.setBirthDate(faker.date().birthday().toString());
            customer.setSex(faker.options().option("MASCULINO", "FEMENINO"));

            List<Telephone> telephones = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                Telephone telephone = new Telephone();
                telephone.setNumber(faker.phoneNumber().cellPhone());
                telephone.setType(faker.options().option("CASA", "TRABAJO", "MOVIL"));
                telephones.add(telephone);
            }
            customer.setTelephones(telephones);
            customerRepository.save(customer);
        }

        // Add your customer seeding logic here
    }

}

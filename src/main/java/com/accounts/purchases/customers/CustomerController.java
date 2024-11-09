package com.accounts.purchases.customers;

import com.accounts.purchases.customers.entities.Customer;
import com.accounts.purchases.customers.entities.Telephone;
import com.accounts.purchases.customers.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @QueryMapping
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @QueryMapping
    public Customer findCustomerById(@Argument String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Customer createCustomer(@Argument String firstName, @Argument String lastName, @Argument String ci,
                                   @Argument String birthDate, @Argument String sex, @Argument List<Telephone> telephones) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setCi(ci);
        customer.setBirthDate(birthDate);
        customer.setSex(sex);
        customer.setTelephones(telephones);
        return customerRepository.save(customer);
    }

    @MutationMapping
    public Customer updateCustomer(@Argument String id, @Argument String firstName, @Argument String lastName,
                                   @Argument String ci, @Argument String birthDate, @Argument String sex,
                                   @Argument List<Telephone> telephones) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
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

    @MutationMapping
    public String deleteCustomer(@Argument String id) {
        customerRepository.deleteById(id);
        return "Customer deleted successfully";
    }


}

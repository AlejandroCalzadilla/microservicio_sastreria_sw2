package com.accounts.auth.controller;

import com.accounts.auth.config.JwtUtils;
import com.accounts.auth.domain.AuthPayload;
import com.accounts.auth.domain.User;
import com.accounts.auth.domain.UserRepository;

import com.accounts.cliente.modelos.Cliente;
import com.accounts.cliente.modelos.Telephone;
import com.accounts.cliente.repositorios.ClienteRepository;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@Slf4j
public class AccountsController {


    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value ("${app.systemUser.login.user}")
    private String systemUser;

    @Value ("${app.systemUser.login.password}")
    private String systemPassword;

    @Autowired
    private JwtUtils jwUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private ClienteRepository customerRepository;


    /*
    @QueryMapping
    public List<BankAccount> accounts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Is User Authenticated : " + authentication.isAuthenticated());
        return bankService.getAccounts();
    }*/

    /*
    @QueryMapping (name = "login")
    public AuthPayload loginQuery (@Argument String email, @Argument String password) {

        if (systemUser.equals(email) && systemPassword.equals(password)) {
            AuthPayload payload = new AuthPayload(jwUtils.generateJWTToken(), new User("Login User", email, email));
            return payload;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
    */

    @QueryMapping(name = "login")
    public AuthPayload loginQuery(@Argument String email, @Argument String password) {
        System.out.println("login llega angular");
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            User user = userOptional.get();
            AuthPayload payload = new AuthPayload(jwUtils.generateJWTToken(), new User(user.getId(), user.getUsername(), user.getEmail(), user.getPassword()));
            return payload;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @MutationMapping(name = "register")
    public User register(@Argument String username, @Argument String email, @Argument String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(null, username, email, encodedPassword);
        return userRepository.save(newUser);
    }


    @MutationMapping(name = "registerCliente")
    public User registerCliente(@Argument String username, @Argument String email, @Argument String password,
                                @Argument String firstName, @Argument String lastName, @Argument String ci,
                                @Argument String birthDate, @Argument String sex, @Argument List<Telephone> telephones) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(null, username, email, encodedPassword);
        User savedUser = userRepository.save(newUser);

        Cliente customer = new Cliente();
        customer.setUserid(savedUser.getId());
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setCi(ci);
        customer.setBirthDate(birthDate);
        customer.setSex(sex);

        List<Telephone> telephoneList = telephones.stream().map(t -> {
            Telephone telephone = new Telephone();
            telephone.setNumber(t.getNumber());
            telephone.setType(t.getType());
            return telephone;
        }).collect(Collectors.toList());
        customer.setTelephones(telephoneList);
        customerRepository.save(customer);

        return savedUser;
    }



    /*
    @SchemaMapping(typeName = "BankAccount", field = "client")
    public Client getClient(BankAccount account) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        //log.info("Getting client for " + account.id() + " for user: " + authentication.getName());
        return bankService.getClientByAccountId(account.id());
    }
    */
    @GraphQlExceptionHandler
    public GraphQLError handle(@NonNull Exception ex, @NonNull DataFetchingEnvironment environment) {
        return GraphQLError
                .newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(ex.getMessage())
                .path(environment.getExecutionStepInfo().getPath())
                .location(environment.getField().getSourceLocation())
                .build();
    }
}

package com.accounts.seeders;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class DataSeederController {


    @Autowired
    private DataSeeder dataSeeder;


    @MutationMapping
    public String seedData() {
        try {
            dataSeeder.seed();
            return "Datos sembrados exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al sembrar datos: " + e.getMessage();
        }
    }


}

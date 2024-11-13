package com.accounts.order.controllers;

import com.accounts.order.entities.Garment;
import com.accounts.order.repositories.GarmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GarmentController {
    @Autowired
    private GarmentRepository garmentRepository;




    @QueryMapping
    public List<Garment> getAllGarment() {
        return garmentRepository.findAll();
    }


    @QueryMapping
    public Garment getGarment(@Argument String id) {
        return garmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garment not found with ID: " + id));
    }

    @MutationMapping
    public Garment createGarment(@Argument String name, @Argument String description, @Argument Double basePrice, @Argument String image) {
        Garment garment = new Garment();
        garment.setName(name);
        garment.setDescription(description);
        garment.setBasePrice(basePrice);
        garment.setImage(image);
        return garmentRepository.save(garment);
    }

    @MutationMapping
    public Garment updateGarment(@Argument String id, @Argument String name, @Argument String description, @Argument Double basePrice, @Argument String imageurl) {
        Garment garment = garmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garment not found with ID: " + id));

        if (name != null) garment.setName(name);
        if (description != null) garment.setDescription(description);
        if (basePrice != null) garment.setBasePrice(basePrice);
        if (imageurl != null) garment.setImage(imageurl);

        return garmentRepository.save(garment);
    }

    @MutationMapping
    public boolean deleteGarment(@Argument String id) {
        Optional<Garment> garment = garmentRepository.findById(id);

        if (garment.isPresent()) {
            garmentRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Garment not found with ID: " + id);
        }
    }

}

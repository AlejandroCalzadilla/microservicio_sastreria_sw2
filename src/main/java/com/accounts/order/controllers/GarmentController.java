package com.accounts.order.controllers;

import com.accounts.imagenes.FileStorageService;
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
    @Autowired
    private FileStorageService fileStorageService;




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
    public Garment createGarment(@Argument String name, @Argument String description, @Argument Double basePrice, @Argument String image, @Argument Integer stock) {
        Garment garment = new Garment();
        garment.setName(name);
        garment.setDescription(description);
        garment.setBasePrice(basePrice);
        garment.setStock(stock);
        garment.setImage(image); // Save the base64 string directly
        return garmentRepository.save(garment);
    }

    @MutationMapping
    public Garment updateGarment(@Argument String id, @Argument String name, @Argument String description, @Argument Double basePrice, @Argument String image,@Argument Integer stock) {
        Garment garment = garmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garment not found with ID: " + id));

        if (name != null) garment.setName(name);
        if (description != null) garment.setDescription(description);
        if (basePrice != null) garment.setBasePrice(basePrice);
        if (image != null) garment.setImage(image);
        if (stock!= null) garment.setStock(stock);

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

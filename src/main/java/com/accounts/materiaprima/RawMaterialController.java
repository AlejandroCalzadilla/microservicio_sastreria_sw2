package com.accounts.materiaprima;

import com.accounts.materiaprima.models.RawMaterial;
import com.accounts.materiaprima.repositories.RawMaterialRepository;
import com.accounts.utils.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

@Controller
public class RawMaterialController {
    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @QueryMapping
    public PaginatedResponse<RawMaterial> findAllRawMaterials(@Argument int limit, @Argument int offset) {
        List<RawMaterial> allMaterials = rawMaterialRepository.findAll();
        int total = allMaterials.size();

        int end = Math.min(offset + limit, allMaterials.size());
        List<RawMaterial> paginatedMaterials = offset > allMaterials.size() ?
                Collections.emptyList() :
                allMaterials.subList(offset, end);
        return new PaginatedResponse<>(paginatedMaterials, total);
    }


    @QueryMapping
    public RawMaterial findRawMaterialById(@Argument String id) {
        return rawMaterialRepository.findById(id).orElse(null);
    }




    @MutationMapping
    public RawMaterial createRawMaterial(@Argument String name, @Argument String unit) {
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setName(name);
        rawMaterial.setUnit(unit);
        return rawMaterialRepository.save(rawMaterial);
    }



    @MutationMapping
    public RawMaterial updateRawMaterial(@Argument String id, @Argument String name, @Argument String unit) {
        return rawMaterialRepository.findById(id).map(rawMaterial -> {
            if (name != null) rawMaterial.setName(name);
            if (unit != null) rawMaterial.setUnit(unit);

            return rawMaterialRepository.save(rawMaterial);
        }).orElse(null);
    }


    @MutationMapping
    public String deleteRawMaterial(@Argument String id) {
        rawMaterialRepository.deleteById(id);
        return "RawMaterial deleted successfully";
    }

}

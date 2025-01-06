package com.accounts.seeders;


import com.accounts.materiaprima.models.RawMaterial;
import com.accounts.materiaprima.repositories.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RawMaterialSeeder {

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    public void seed() {
        List<RawMaterial> rawMaterials = new ArrayList<>();



        RawMaterial rawMaterial1 = new RawMaterial();
        rawMaterial1.setName("Tela de algodón popelín");
        rawMaterial1.setUnit("metros");
        rawMaterials.add(rawMaterial1);

        RawMaterial rawMaterial2 = new RawMaterial();
        rawMaterial2.setName("Tela de algodón piqué");
        rawMaterial2.setUnit("metros");
        rawMaterials.add(rawMaterial2);

        RawMaterial rawMaterial3 = new RawMaterial();
        rawMaterial3.setName("Tela de algodón oxford");
        rawMaterial3.setUnit("metros");
        rawMaterials.add(rawMaterial3);

        RawMaterial rawMaterial4 = new RawMaterial();
        rawMaterial4.setName("Tela de algodón gabardina");
        rawMaterial4.setUnit("metros");
        rawMaterials.add(rawMaterial4);

        RawMaterial rawMaterial5 = new RawMaterial();
        rawMaterial5.setName("Tela de algodón denim (jeans)");
        rawMaterial5.setUnit("metros");
        rawMaterials.add(rawMaterial5);

        RawMaterial rawMaterial6 = new RawMaterial();
        rawMaterial6.setName("Tela de lino natural");
        rawMaterial6.setUnit("metros");
        rawMaterials.add(rawMaterial6);

        RawMaterial rawMaterial7 = new RawMaterial();
        rawMaterial7.setName("Tela de lana virgen");
        rawMaterial7.setUnit("metros");
        rawMaterials.add(rawMaterial7);

        RawMaterial rawMaterial8 = new RawMaterial();
        rawMaterial8.setName("Tela de lana fría");
        rawMaterial8.setUnit("metros");
        rawMaterials.add(rawMaterial8);

        RawMaterial rawMaterial9 = new RawMaterial();
        rawMaterial9.setName("Tela de lana tweed");
        rawMaterial9.setUnit("metros");
        rawMaterials.add(rawMaterial9);

        RawMaterial rawMaterial10 = new RawMaterial();
        rawMaterial10.setName("Tela de seda natural");
        rawMaterial10.setUnit("metros");
        rawMaterials.add(rawMaterial10);

        RawMaterial rawMaterial11 = new RawMaterial();
        rawMaterial11.setName("Tela de seda satén");
        rawMaterial11.setUnit("metros");
        rawMaterials.add(rawMaterial11);

        RawMaterial rawMaterial12 = new RawMaterial();
        rawMaterial12.setName("Tela de seda tafetán");
        rawMaterial12.setUnit("metros");
        rawMaterials.add(rawMaterial12);

        RawMaterial rawMaterial13 = new RawMaterial();
        rawMaterial13.setName("Tela de terciopelo");
        rawMaterial13.setUnit("metros");
        rawMaterials.add(rawMaterial13);

        RawMaterial rawMaterial14 = new RawMaterial();
        rawMaterial14.setName("Tela de gamuza");
        rawMaterial14.setUnit("metros");
        rawMaterials.add(rawMaterial14);

        RawMaterial rawMaterial15 = new RawMaterial();
        rawMaterial15.setName("Tela de cuero genuino (vacuno)");
        rawMaterial15.setUnit("metros");
        rawMaterials.add(rawMaterial15);

        RawMaterial rawMaterial16 = new RawMaterial();
        rawMaterial16.setName("Tela de cuero sintético (imitación)");
        rawMaterial16.setUnit("metros");
        rawMaterials.add(rawMaterial16);

        RawMaterial rawMaterial17 = new RawMaterial();
        rawMaterial17.setName("Tela de encaje");
        rawMaterial17.setUnit("metros");
        rawMaterials.add(rawMaterial17);

        RawMaterial rawMaterial18 = new RawMaterial();
        rawMaterial18.setName("Tela de organza");
        rawMaterial18.setUnit("metros");
        rawMaterials.add(rawMaterial18);

        RawMaterial rawMaterial19 = new RawMaterial();
        rawMaterial19.setName("Tela de tul");
        rawMaterial19.setUnit("metros");
        rawMaterials.add(rawMaterial19);

        RawMaterial rawMaterial20 = new RawMaterial();
        rawMaterial20.setName("Tela de tafetán de seda");
        rawMaterial20.setUnit("metros");
        rawMaterials.add(rawMaterial20);

        //HILO

        RawMaterial rawMaterial21 = new RawMaterial();
        rawMaterial21.setName("Hilo de poliéster común");
        rawMaterial21.setUnit("carrete");
        rawMaterials.add(rawMaterial21);

        RawMaterial rawMaterial22 = new RawMaterial();
        rawMaterial22.setName("Hilo de poliéster encerado");
        rawMaterial22.setUnit("carrete");
        rawMaterials.add(rawMaterial22);

        RawMaterial rawMaterial23 = new RawMaterial();
        rawMaterial23.setName("Hilo de algodón común");
        rawMaterial23.setUnit("carrete");
        rawMaterials.add(rawMaterial23);

        RawMaterial rawMaterial24 = new RawMaterial();
        rawMaterial24.setName("Hilo de algodón mercerizado");
        rawMaterial24.setUnit("carrete");
        rawMaterials.add(rawMaterial24);

        RawMaterial rawMaterial25 = new RawMaterial();
        rawMaterial25.setName("Hilo de nylon");
        rawMaterial25.setUnit("carrete");
        rawMaterials.add(rawMaterial25);

        RawMaterial rawMaterial26 = new RawMaterial();
        rawMaterial26.setName("Hilo de seda");
        rawMaterial26.setUnit("carrete");
        rawMaterials.add(rawMaterial26);

        //FORRO

        RawMaterial rawMaterial27 = new RawMaterial();
        rawMaterial27.setName("Forro de acetato");
        rawMaterial27.setUnit("metros");
        rawMaterials.add(rawMaterial27);

        RawMaterial rawMaterial28 = new RawMaterial();
        rawMaterial28.setName("Forro de rayón");
        rawMaterial28.setUnit("metros");
        rawMaterials.add(rawMaterial28);

        RawMaterial rawMaterial29 = new RawMaterial();
        rawMaterial29.setName("Forro de algodón");
        rawMaterial29.setUnit("metros");
        rawMaterials.add(rawMaterial29);

        RawMaterial rawMaterial30 = new RawMaterial();
        rawMaterial30.setName("Forro de tafetán");
        rawMaterial30.setUnit("metros");
        rawMaterials.add(rawMaterial30);

        //BOTONES

        RawMaterial rawMaterial31 = new RawMaterial();
        rawMaterial31.setName("Botones de plástico");
        rawMaterial31.setUnit("paquete");
        rawMaterials.add(rawMaterial31);

        RawMaterial rawMaterial32 = new RawMaterial();
        rawMaterial32.setName("Botones de metal");
        rawMaterial32.setUnit("paquete");
        rawMaterials.add(rawMaterial32);

        RawMaterial rawMaterial33 = new RawMaterial();
        rawMaterial33.setName("Botones de nácar");
        // Add more raw materials as needed

        for (RawMaterial rawMaterial : rawMaterials) {
            rawMaterialRepository.save(rawMaterial);
        }
    }
}
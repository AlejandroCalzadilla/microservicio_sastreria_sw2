package com.accounts.seeders;

import com.accounts.order.entities.Garment;
import com.accounts.order.repositories.GarmentRepository;
import net.datafaker.Faker;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class GarmentSeeder {

    @Autowired
    private GarmentRepository garmentRepository;

    private static final String IMAGE_DIR = "C:/Users/ASUS/Desktop/Nueva carpeta/microservicio_sastreria_sw2/src/main/java/com/accounts/Imagenes/vestimentas/";

    public void seed() {
        Faker faker = new Faker();
        List<Garment> garments = new ArrayList<>();

        List<String> garmentNames = List.of("saco", "camisa", "pantalon", "chaleco");
        List<Integer> garmentImageCounts = List.of(5, 0, 4, 4); // Number of images available for each garment type

        for (int i = 0; i < 30; i++) {
            Garment garment = new Garment();
            int garmentIndex = faker.number().numberBetween(0, garmentNames.size());
            String garmentName = garmentNames.get(garmentIndex);
            garment.setName(garmentName);
            garment.setDescription(faker.lorem().paragraph(2)); // Generar descripción de 2 párrafos
            garment.setStock(faker.number().numberBetween(1, 3));
            garment.setBasePrice(Double.valueOf(faker.commerce().price()));

            // Generar el nombre del archivo de imagen basado en el nombre de la prenda y el índice
            int imageIndex = faker.number().numberBetween(1, garmentImageCounts.get(garmentIndex) + 1);
            String imageName = generateImageName(garmentName, imageIndex);
            try {
                String base64Image = convertImageToBase64(IMAGE_DIR + imageName);
                garment.setImage(base64Image);
            } catch (IOException e) {
                e.printStackTrace();
                garment.setImage(""); // Guardar un string vacío si la imagen no se encuentra
            }

            garments.add(garment);
        }

        // Guardar todas las prendas en una transacción (opcional para eficiencia)
        garmentRepository.saveAll(garments);
    }

    private String generateImageName(String garmentName, int index) {
        return garmentName + index + ".jpg";
    }

    private String convertImageToBase64(String imagePath) throws IOException {
        if (Files.exists(Paths.get(imagePath))) {
            byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
            return Base64.encodeBase64String(imageBytes);
        } else {
            return "";
        }
    }
}
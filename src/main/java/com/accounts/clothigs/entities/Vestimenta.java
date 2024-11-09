package com.accounts.clothigs.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vestimenta")
public record Vestimenta(@Id String id, String nombre, String genero, UnidadVestimenta unidadVestimenta, Medida medida) {
}

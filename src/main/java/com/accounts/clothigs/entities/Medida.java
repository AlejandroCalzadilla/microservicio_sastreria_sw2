package com.accounts.clothigs.entities;

import org.springframework.data.annotation.Id;

public record Medida(@Id String id, String nombre, MedidaVestimenta medidaVestimenta) {
}

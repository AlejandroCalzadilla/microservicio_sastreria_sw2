package com.accounts.clothigs.entities;

import org.springframework.data.annotation.Id;

public record UnidadVestimenta(@Id String id, boolean estado, MedidaVestimenta medidaVestimenta) {
}

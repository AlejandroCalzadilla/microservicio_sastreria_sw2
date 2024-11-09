package com.accounts.clothigs.entities;

import org.springframework.data.annotation.Id;

public record MedidaVestimenta(@Id String id, float valor, Cambio cambio) {
}

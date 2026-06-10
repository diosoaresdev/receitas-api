package com.receitas.dto;

import java.util.UUID;

public record IngredienteItemRequest(
        UUID ingredienteId,
        Double quantidade
) {
}

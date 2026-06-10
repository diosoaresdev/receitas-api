package com.receitas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record IngredienteItemRequest(

        @NotNull(message = "ID do ingrediente é obrigatório")
        UUID ingredienteId,

        @NotNull(message = "Quantidade é obrigatória")
        @Min(value = 0, message = "Quantidade deve ser maior que zero")
        Double quantidade
) {
}

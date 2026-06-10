package com.receitas.dto;

import com.receitas.domain.Receita;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record ReceitaRequest(

        @NotBlank(message = "Título é obrigatório")
        String titulo,


        String descricao,

        @NotNull(message = "Porções é obrigatório")
        @Min(value = 1, message = "Porções deve ser no mínimo 1")
        Integer porcoes,

        @NotNull(message = "Tempo de preparo é obrigatório")
        @Min(value = 1, message = "Tempo de preparo deve ser no mínimo 1 minuto")
        Integer tempoPreparo,

        @NotNull(message = "Nível é obrigatório")
        Receita.Nivel nivel,

        @NotNull(message = "Categoria é obrigatória")
        UUID categoriaId,

        @NotEmpty(message = "A receita deve ter pelo menos um ingrediente")
        @Valid
        List<IngredienteItemRequest> ingredientes
) {
}

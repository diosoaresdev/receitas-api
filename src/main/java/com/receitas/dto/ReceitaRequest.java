package com.receitas.dto;

import com.receitas.domain.Receita;

import java.util.List;
import java.util.UUID;

public record ReceitaRequest(
        String titulo,
        String descricao,
        Integer porcoes,
        Integer tempoPreparo,
        Receita.Nivel nivel,
        UUID categoriaId,
        List<IngredienteItemRequest> ingredientes
) {
}

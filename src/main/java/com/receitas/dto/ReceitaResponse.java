package com.receitas.dto;

import com.receitas.domain.Receita;
import com.receitas.domain.Receita.Nivel;
import java.util.List;
import java.util.UUID;

public record ReceitaResponse(
        UUID id,
        String titulo,
        String descricao,
        Integer porcoes,
        Integer tempoPreparo,
        Nivel nivel,
        String categoria,
        List<IngredienteResponse> ingredientes
) {
    public record IngredienteResponse(
            UUID id,
            String nome,
            String unidadeMedida,
            Double quantidade
    ) {}

    public static ReceitaResponse from(Receita receita) {
        return new ReceitaResponse(
                receita.getId(),
                receita.getTitulo(),
                receita.getDescricao(),
                receita.getPorcoes(),
                receita.getTempoPreparo(),
                receita.getNivel(),
                receita.getCategoria() != null ? receita.getCategoria().getNome() : null,
                receita.getIngredientes().stream()
                        .map(ri -> new IngredienteResponse(
                                ri.getIngrediente().getId(),
                                ri.getIngrediente().getNome(),
                                ri.getIngrediente().getUnidadeMedida(),
                                ri.getQuantidade()
                        ))
                        .toList()
        );
    }
}
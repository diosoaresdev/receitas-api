package com.receitas.service;

import com.receitas.domain.Categoria;
import com.receitas.domain.Receita;
import com.receitas.domain.ReceitaIngrediente;
import com.receitas.dto.ReceitaRequest;
import com.receitas.dto.ReceitaResponse;
import com.receitas.domain.Receita.Nivel;
import com.receitas.repository.CategoriaRepository;
import com.receitas.repository.IngredienteRepository;
import com.receitas.repository.ReceitaRepository;
import com.receitas.specification.ReceitaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReceitaService {

    private final ReceitaRepository receitaRepository;
    private final CategoriaRepository categoriaRepository;
    private final IngredienteRepository ingredienteRepository;

    @Transactional
    public ReceitaResponse criar(ReceitaRequest request) {
        Categoria categoria = categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Receita receita = new Receita();
        receita.setTitulo(request.titulo());
        receita.setDescricao(request.descricao());
        receita.setPorcoes(request.porcoes());
        receita.setTempoPreparo(request.tempoPreparo());
        receita.setNivel(request.nivel());
        receita.setCategoria(categoria);

        request.ingredientes().forEach(item -> {
            var ingrediente = ingredienteRepository.findById(item.ingredienteId())
                    .orElseThrow(() -> new RuntimeException("Ingrediente não encontrado: " + item.ingredienteId()));

            var receitaIngrediente = new ReceitaIngrediente();
            receitaIngrediente.setReceita(receita);
            receitaIngrediente.setIngrediente(ingrediente);
            receitaIngrediente.setQuantidade(item.quantidade());

            receita.getIngredientes().add(receitaIngrediente);
        });

        return ReceitaResponse.from(receitaRepository.save(receita));
    }

    @Transactional(readOnly = true)
    public List<ReceitaResponse> listar(String categoria, Nivel nivel, Integer maxTempo, String titulo) {
        Specification<Receita> spec = Specification
                .where(ReceitaSpecification.comCategoria(categoria))
                .and(ReceitaSpecification.comNivel(nivel))
                .and(ReceitaSpecification.comMaxTempo(maxTempo))
                .and(ReceitaSpecification.comTitulo(titulo));

        return receitaRepository.findAll(spec)
                .stream()
                .map(ReceitaResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public ReceitaResponse buscarPorId(UUID id) {
        return receitaRepository.findById(id)
                .map(ReceitaResponse::from)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));
    }

    @Transactional
    public void deletar(UUID id) {
        if (!receitaRepository.existsById(id)) {
            throw new RuntimeException("Receita não encontrada");
        }
        receitaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ReceitaResponse calcularPorcoes(UUID id, Integer porcoes) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        if (porcoes < 1) {
            throw new RuntimeException("Quantidade de porções deve ser maior que zero");
        }

        double fator = (double) porcoes / receita.getPorcoes();

        List<ReceitaResponse.IngredienteResponse> ingredientesAjustados = receita.getIngredientes()
                .stream()
                .map(ri -> new ReceitaResponse.IngredienteResponse(
                        ri.getIngrediente().getId(),
                        ri.getIngrediente().getNome(),
                        ri.getIngrediente().getUnidadeMedida(),
                        Math.round(ri.getQuantidade() * fator * 100.0) / 100.0
                ))
                .toList();

        return new ReceitaResponse(
                receita.getId(),
                receita.getTitulo(),
                receita.getDescricao(),
                porcoes,
                receita.getTempoPreparo(),
                receita.getNivel(),
                receita.getCategoria() != null ? receita.getCategoria().getNome() : null,
                ingredientesAjustados
        );
    }
}
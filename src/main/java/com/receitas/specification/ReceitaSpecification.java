package com.receitas.specification;

import com.receitas.domain.Receita;
import com.receitas.domain.Receita.Nivel;
import org.springframework.data.jpa.domain.Specification;

public class ReceitaSpecification {

    public static Specification<Receita> comCategoria(String categoria) {
        return (root, query, cb) -> {
            if (categoria == null || categoria.isBlank()) return null;
            return cb.like(
                    cb.lower(root.get("categoria").get("nome")),
                    "%" + categoria.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Receita> comNivel(Nivel nivel) {
        return (root, query, cb) -> {
            if (nivel == null) return null;
            return cb.equal(root.get("nivel"), nivel);
        };
    }

    public static Specification<Receita> comMaxTempo(Integer maxTempo) {
        return (root, query, cb) -> {
            if (maxTempo == null) return null;
            return cb.lessThanOrEqualTo(root.get("tempoPreparo"), maxTempo);
        };
    }

    public static Specification<Receita> comTitulo(String titulo) {
        return (root, query, cb) -> {
            if (titulo == null || titulo.isBlank()) return null;
            return cb.like(
                    cb.lower(root.get("titulo")),
                    "%" + titulo.toLowerCase() + "%"
            );
        };
    }
}
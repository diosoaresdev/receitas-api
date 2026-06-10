package com.receitas.repository;

import com.receitas.domain.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngredienteRepository extends JpaRepository<Ingrediente, UUID> {
}

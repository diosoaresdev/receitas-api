package com.receitas.repository;

import com.receitas.domain.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReceitaRepository extends JpaRepository<Receita, UUID> {
}

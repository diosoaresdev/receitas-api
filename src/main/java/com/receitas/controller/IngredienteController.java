package com.receitas.controller;

import com.receitas.domain.Ingrediente;
import com.receitas.repository.IngredienteRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {

    private final IngredienteRepository repository;

    @GetMapping
    public List<Ingrediente> listar() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingrediente criar(@RequestBody @Valid Ingrediente ingrediente) {
        return repository.save(ingrediente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}

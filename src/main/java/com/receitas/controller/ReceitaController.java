package com.receitas.controller;

import com.receitas.dto.ReceitaRequest;
import com.receitas.dto.ReceitaResponse;
import com.receitas.service.ReceitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/receitas")
@RequiredArgsConstructor
public class ReceitaController {

    private final ReceitaService receitaService;

    @GetMapping
    public List<ReceitaResponse> listar() {
        return receitaService.listar();
    }

    @GetMapping("/{id}")
    public ReceitaResponse buscarPorId(@PathVariable UUID id) {
        return receitaService.buscarPorId(id);
    }

    @GetMapping("/{id}/porcoes")
    public ReceitaResponse calcularPorcoes(
            @PathVariable UUID id,
            @RequestParam Integer qtd) {
        return receitaService.calcularPorcoes(id, qtd);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReceitaResponse criar(@RequestBody @Valid ReceitaRequest request) {
        return receitaService.criar(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id) {
        receitaService.deletar(id);
    }
}

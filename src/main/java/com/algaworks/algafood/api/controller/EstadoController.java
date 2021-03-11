package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

    @Autowired
     private CadastroEstadoService cadastroEstadoService;


    @GetMapping
    public List<Estado> listar() {
        return cadastroEstadoService.listar();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long restauranteId) {
        Estado estado = cadastroEstadoService.buscar(restauranteId);

        if (estado != null) {
            return ResponseEntity.ok(estado);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Estado estado) {
        try {
            Estado estadoResp = cadastroEstadoService.salvar(estado);
            return ResponseEntity.ok(estadoResp);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

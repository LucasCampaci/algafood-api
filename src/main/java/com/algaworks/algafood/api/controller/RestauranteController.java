package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public List<Restaurante> listar() {
        return cadastroRestauranteService.listar();
    }

//    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
//    public CozinhasXmlWrapper listarXml() {
//        return new CozinhasXmlWrapper(cadastroRestauranteService.listar());
//    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscar(restauranteId);

        if (restaurante != null) {
            return ResponseEntity.ok(restaurante);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteResp = cadastroRestauranteService.salvar(restaurante);
            return ResponseEntity.ok(restauranteResp);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> alterar(@PathVariable Long restauranteId, @RequestBody Restaurante restauranteRQ) {
        try {
            Restaurante restaurante = cadastroRestauranteService.buscar(restauranteId);

            if (restaurante != null) {
                    BeanUtils.copyProperties(restauranteRQ, restaurante, "id");
                    Restaurante restauranteResp = cadastroRestauranteService.salvar(restaurante);
                    return ResponseEntity.ok(restauranteResp);
            }
            return ResponseEntity.notFound().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{restauranteId}")
    public  ResponseEntity<Restaurante> excluir(@PathVariable Long restauranteId) {
        try {
            cadastroRestauranteService.remover(restauranteId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
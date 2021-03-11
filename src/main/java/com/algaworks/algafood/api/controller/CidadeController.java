package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @GetMapping
    public List<Cidade> listar() {
        return cadastroCidadeService.listar();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidadeService.buscar(cidadeId);

        if (cidade != null) {
            return ResponseEntity.ok(cidade);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cidade) {
        Cidade cidadeResp = cadastroCidadeService.salvar(cidade);
        return ResponseEntity.ok(cidadeResp);
    }

    @PutMapping("/{cozinhaId}")
    public  ResponseEntity<Cidade> alterar(@PathVariable Long cidadeId, @RequestBody Cidade cidadeRQ) {
        Cidade cidade = cadastroCidadeService.buscar(cidadeId);

        if (cidade != null) {
            BeanUtils.copyProperties(cidadeRQ, cidade, "id");
            Cidade cidadeResp = cadastroCidadeService.salvar(cidade);
            return ResponseEntity.ok(cidadeResp);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cozinhaId}")
    public  ResponseEntity<Cidade> excluir(@PathVariable Long cidadeId) {
        try {
            cadastroCidadeService.remover(cidadeId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
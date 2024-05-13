package com.algaworks.algafood.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/{operacao}/{num1}/{num2}")
    public ResponseEntity<?> buscar(@PathVariable String operacao,
                                    @PathVariable Double num1,
                                    @PathVariable Double num2) {
        double resultado = 0;
        switch (operacao) {
            case "sum" -> resultado = num1 + num2;
            case "sub" -> resultado = num1 - num2;
            case "x" -> resultado = num1 * num2;
        }
        return ResponseEntity.ok(resultado);
    }
}
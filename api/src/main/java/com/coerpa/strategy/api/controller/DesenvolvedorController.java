package com.coerpa.strategy.api.controller;

import com.coerpa.strategy.api.model.Desenvolvedor;
import com.coerpa.strategy.api.service.DesenvolvedorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/desenvolvedor")
@RequiredArgsConstructor
public class DesenvolvedorController {
    private final DesenvolvedorService desenvolvedorService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDesenvolvedores() throws JsonProcessingException {
        return desenvolvedorService.getDesenvolvedores();
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDesenvolvedor(@PathVariable(value = "id") Long id) throws JsonProcessingException {
        return desenvolvedorService.getDesenvolvedor(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createDesenvolvedor (@Valid @RequestBody Desenvolvedor desenvolvedor){
        return desenvolvedorService.createDesenvolvedor(desenvolvedor);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateDesenvolvedor (@PathVariable(value = "id") Long id, @Valid @RequestBody Desenvolvedor desenvolvedor){
        return desenvolvedorService.updateDesenvolvedor(desenvolvedor, id);
    }
}

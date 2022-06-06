package com.coerpa.strategy.api.controller;

import com.coerpa.strategy.api.model.Origem;
import com.coerpa.strategy.api.service.OrigemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/origem")
@RequiredArgsConstructor
public class OrigemController {
    private final OrigemService origemService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOrigens() throws JsonProcessingException {
        return origemService.getOrigens();
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOrigem(@PathVariable(value = "id") Long id) throws JsonProcessingException {
        return origemService.getOrigem(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createOrigem (@Valid @RequestBody Origem origem){
        return origemService.createOrigem(origem);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateOrigem (@PathVariable(value = "id") Long id, @Valid @RequestBody Origem origem){
        return origemService.updateOrigem(origem, id);
    }
}

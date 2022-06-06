package com.coerpa.strategy.api.controller;

import com.coerpa.strategy.api.model.Responsavel;
import com.coerpa.strategy.api.service.ResponsavelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/responsavel")
@RequiredArgsConstructor
public class ResponsavelController {
    private final ResponsavelService responsavelService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getResponsaveis() throws JsonProcessingException {
        return responsavelService.getResponsaveis();
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getResponsavel(@PathVariable(value = "id") Long id) throws JsonProcessingException {
        return responsavelService.getResponsavel(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createResponsavel (@Valid @RequestBody Responsavel responsavel){
        return responsavelService.createResponsavel(responsavel);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateResponsavel (@PathVariable(value = "id") Long id, @Valid @RequestBody Responsavel responsavel){
        return responsavelService.updateResponsavel(responsavel, id);
    }
}

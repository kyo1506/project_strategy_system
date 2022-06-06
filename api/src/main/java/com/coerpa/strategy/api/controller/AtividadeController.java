package com.coerpa.strategy.api.controller;

import com.coerpa.strategy.api.model.Atividade;
import com.coerpa.strategy.api.model.AtividadeId;
import com.coerpa.strategy.api.service.AtividadeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/atividade")
@RequiredArgsConstructor
public class AtividadeController {
    private final AtividadeService atividadeService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAtividades() throws JsonProcessingException {
        return atividadeService.getAtividades();
    }

    @GetMapping(value = "/sequence/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> generateSequence(@PathVariable(value = "id") AtividadeId id) {
        return atividadeService.generateSequence(id);
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAtividade(@PathVariable(value = "id") AtividadeId id) throws JsonProcessingException {
        return atividadeService.getAtividade(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAtividade (@Valid @RequestBody Atividade atividade){
        return atividadeService.createAtividade(atividade);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateAtividade (@PathVariable(value = "id") AtividadeId id, @Valid @RequestBody Atividade atividade){
        return atividadeService.updateAtividade(atividade, id);
    }
}

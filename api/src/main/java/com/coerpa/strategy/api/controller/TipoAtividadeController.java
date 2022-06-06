package com.coerpa.strategy.api.controller;

import com.coerpa.strategy.api.model.TipoAtividade;
import com.coerpa.strategy.api.service.TipoAtividadeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tipoAtividade")
@RequiredArgsConstructor
public class TipoAtividadeController {
    private final TipoAtividadeService tipoAtividadeService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAtividades() throws JsonProcessingException {
        return tipoAtividadeService.getTipoAtividades();
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAtividade(@PathVariable(value = "id") Long id) throws JsonProcessingException {
        return tipoAtividadeService.getTipoAtividade(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAtividade (@Valid @RequestBody TipoAtividade tipoAtividade){
        return tipoAtividadeService.createTipoAtividade(tipoAtividade);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateAtividade (@PathVariable(value = "id") Long id, @Valid @RequestBody TipoAtividade tipoAtividade){
        return tipoAtividadeService.updateTipoAtividade(tipoAtividade, id);
    }
}

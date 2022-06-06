package com.coerpa.strategy.api.controller;

import com.coerpa.strategy.api.model.Cliente;
import com.coerpa.strategy.api.service.ClienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getClientes() throws JsonProcessingException {
        return clienteService.getClientes();
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCliente(@PathVariable(value = "id") Long id) throws JsonProcessingException {
        return clienteService.getCliente(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCliente (@Valid @RequestBody Cliente cliente){
        return clienteService.createCliente(cliente);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCliente (@PathVariable(value = "id") Long id, @Valid @RequestBody Cliente cliente){
        return clienteService.updateCliente(cliente, id);
    }
}

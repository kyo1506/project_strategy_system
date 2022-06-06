package com.coerpa.strategy.api.controller;

import com.coerpa.strategy.api.model.Status;
import com.coerpa.strategy.api.service.StatusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getStatus() throws JsonProcessingException {
        return statusService.getStatus();
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getStatus(@PathVariable(value = "id") Long id) throws JsonProcessingException {
        return statusService.getStatus(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createStatus (@Valid @RequestBody Status status){
        return statusService.createStatus(status);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateStatus (@PathVariable(value = "id") Long id, @Valid @RequestBody Status status){
        return statusService.updateStatus(status, id);
    }
}

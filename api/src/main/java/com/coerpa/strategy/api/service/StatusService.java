package com.coerpa.strategy.api.service;

import com.coerpa.strategy.api.model.Status;
import com.coerpa.strategy.api.repository.StatusRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusService {
    private final StatusRepository statusRepository;
    private final ObjectMapper objectMapper;

    public ResponseEntity<Object> getStatus() throws JsonProcessingException {
        List<Status> statusList = statusRepository.findAll();
        if (!statusList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(statusList), HttpStatus.OK);
        } else return new ResponseEntity<>("Não há status cadastrados!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> getStatus(Long id) throws JsonProcessingException {
        Optional<Status> status = statusRepository.findById(id);
        if (status.isPresent()){
            return new ResponseEntity<>(objectMapper.writeValueAsString(status), HttpStatus.OK);
        }else return new ResponseEntity<>("Status não encontrado!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> createStatus(Status model){
        if (!statusRepository.existsStatusByNome(model.getNome())) {
            Status status = new Status();
            status.setNome(model.getNome());
            Status statusSalvo = statusRepository.save(status);
            if (statusRepository.findById(statusSalvo.getId()).isPresent())
                return new ResponseEntity<>("Status cadastrado com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Já existe um status com esse nome!", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    public ResponseEntity<Object> updateStatus(Status model, Long id){
        if (statusRepository.findById(id).isPresent()){
            Status status = statusRepository.findById(id).get();
            status.setNome(model.getNome());
            status.setAtivo(model.getAtivo());
            status.setDtAlteracao(LocalDateTime.now());
            Status statusSalvo = statusRepository.save(status);
            if (statusRepository.findById(statusSalvo.getId()).isPresent())
                return new ResponseEntity<>("Status atualizado com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Status não encontrado!", HttpStatus.NOT_FOUND);
    }
}

package com.coerpa.strategy.api.service;

import com.coerpa.strategy.api.model.Responsavel;
import com.coerpa.strategy.api.repository.ResponsavelRepository;
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
public class ResponsavelService {
    private final ResponsavelRepository responsavelRepository;
    private final ObjectMapper objectMapper;

    public ResponseEntity<Object> getResponsaveis() throws JsonProcessingException {
        List<Responsavel> responsaveisList = responsavelRepository.findAll();
        if (!responsaveisList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(responsaveisList), HttpStatus.OK);
        } else return new ResponseEntity<>("Não há responsáveis cadastrados!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> getResponsavel(Long id) throws JsonProcessingException {
        Optional<Responsavel> responsavel = responsavelRepository.findById(id);
        if (responsavel.isPresent()){
            return new ResponseEntity<>(objectMapper.writeValueAsString(responsavel), HttpStatus.OK);
        }else return new ResponseEntity<>("Responsável não encontrado!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> createResponsavel(Responsavel model){
        if (!responsavelRepository.existsResponsavelByNome(model.getNome())) {
            Responsavel responsavel = new Responsavel();
            responsavel.setNome(model.getNome());
            Responsavel responsavelSalvo = responsavelRepository.save(responsavel);
            if (responsavelRepository.findById(responsavelSalvo.getId()).isPresent())
                return new ResponseEntity<>("Responsavel cadastrado com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Já existe um responsável com esse nome!", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    public ResponseEntity<Object> updateResponsavel(Responsavel model, Long id){
        if (responsavelRepository.findById(id).isPresent()){
            Responsavel responsavel = responsavelRepository.findById(id).get();
            responsavel.setNome(model.getNome());
            responsavel.setAtivo(model.getAtivo());
            responsavel.setDtAlteracao(LocalDateTime.now());
            Responsavel responsavelSalvo = responsavelRepository.save(responsavel);
            if (responsavelRepository.findById(responsavelSalvo.getId()).isPresent())
                return new ResponseEntity<>("Responsável atualizado com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Responsável não encontrado!", HttpStatus.NOT_FOUND);
    }
}

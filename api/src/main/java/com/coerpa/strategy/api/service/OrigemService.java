package com.coerpa.strategy.api.service;

import com.coerpa.strategy.api.model.Origem;
import com.coerpa.strategy.api.repository.OrigemRepository;
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
public class OrigemService {
    private final OrigemRepository origemRepository;
    private final ObjectMapper objectMapper;

    public ResponseEntity<Object> getOrigens() throws JsonProcessingException {
        List<Origem> origensList = origemRepository.findAll();
        if (!origensList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(origensList), HttpStatus.OK);
        } else return new ResponseEntity<>("Não há origens cadastradas!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> getOrigem(Long id) throws JsonProcessingException {
        Optional<Origem> origem = origemRepository.findById(id);
        if (origem.isPresent()){
            return new ResponseEntity<>(objectMapper.writeValueAsString(origem), HttpStatus.OK);
        }else return new ResponseEntity<>("Origem não encontrada!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> createOrigem(Origem model){
        if (!origemRepository.existsOrigemByNome(model.getNome())) {
            Origem origem = new Origem();
            origem.setNome(model.getNome());
            Origem origemSalvo = origemRepository.save(origem);
            if (origemRepository.findById(origemSalvo.getId()).isPresent())
                return new ResponseEntity<>("Origem cadastrada com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Já existe uma origem com esse nome!", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    public ResponseEntity<Object> updateOrigem(Origem model, Long id){
        if (origemRepository.findById(id).isPresent()){
            Origem origem = origemRepository.findById(id).get();
            origem.setNome(model.getNome());
            origem.setAtivo(model.getAtivo());
            origem.setDtAlteracao(LocalDateTime.now());
            Origem origemSalvo = origemRepository.save(origem);
            if (origemRepository.findById(origemSalvo.getId()).isPresent())
                return new ResponseEntity<>("Origem atualizada com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Origem não encontrada!", HttpStatus.NOT_FOUND);
    }
}

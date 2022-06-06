package com.coerpa.strategy.api.service;

import com.coerpa.strategy.api.model.TipoAtividade;
import com.coerpa.strategy.api.repository.TipoAtividadeRepository;
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
public class TipoAtividadeService {
    private final TipoAtividadeRepository tipoAtividadeRepository;
    private final ObjectMapper objectMapper;

    public ResponseEntity<Object> getTipoAtividades() throws JsonProcessingException {
        List<TipoAtividade> tipoAtividadesList = tipoAtividadeRepository.findAll();
        if (!tipoAtividadesList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(tipoAtividadesList), HttpStatus.OK);
        } else return new ResponseEntity<>("Não há tipos de atividades cadastradas!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> getTipoAtividade(Long id) throws JsonProcessingException {
        Optional<TipoAtividade> tipoAtividade = tipoAtividadeRepository.findById(id);
        if (tipoAtividade.isPresent()) return new ResponseEntity<>(objectMapper.writeValueAsString(tipoAtividade), HttpStatus.OK);
        else return new ResponseEntity<>("Tipo de atividade não encontrada!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> createTipoAtividade(TipoAtividade model){
        if (!tipoAtividadeRepository.existsTipoAtividadeByNome(model.getNome())) {
            TipoAtividade tipoAtividade = new TipoAtividade();
            tipoAtividade.setNome(model.getNome());
            TipoAtividade tipoAtividadeSalvo = tipoAtividadeRepository.save(tipoAtividade);
            if (tipoAtividadeRepository.findById(tipoAtividadeSalvo.getId()).isPresent())
                return new ResponseEntity<>("Tipo de atividade cadastrada com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Já existe um tipo de atividade com esse nome!", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    public ResponseEntity<Object> updateTipoAtividade(TipoAtividade model, Long id){
        if (tipoAtividadeRepository.findById(id).isPresent()){
            TipoAtividade tipoAtividade = tipoAtividadeRepository.findById(id).get();
            tipoAtividade.setNome(model.getNome());
            tipoAtividade.setAtivo(model.getAtivo());
            tipoAtividade.setDtAlteracao(LocalDateTime.now());
            TipoAtividade tipoAtividadeSalvo = tipoAtividadeRepository.save(tipoAtividade);
            if (tipoAtividadeRepository.findById(tipoAtividadeSalvo.getId()).isPresent())
                return new ResponseEntity<>("Tipo de atividade atualizada com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Tipo de atividade não encontrada!", HttpStatus.NOT_FOUND);
    }
}

package com.coerpa.strategy.api.service;

import com.coerpa.strategy.api.model.Atividade;
import com.coerpa.strategy.api.model.AtividadeId;
import com.coerpa.strategy.api.repository.AtividadeRepository;
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
public class AtividadeService {
    private final AtividadeRepository atividadeRepository;
    private final ObjectMapper objectMapper;

    public ResponseEntity<Object> generateSequence(AtividadeId id) {
        return new ResponseEntity<>(atividadeRepository.countAtividadeById(id), HttpStatus.OK);
    }
    public ResponseEntity<Object> getAtividades() throws JsonProcessingException {
        List<Atividade> atividadesList = atividadeRepository.findAll();
        if (!atividadesList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(atividadesList), HttpStatus.OK);
        }else return new ResponseEntity<>("Atividades não cadastradas!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> getAtividade(AtividadeId id) throws JsonProcessingException {
        Optional<Atividade> atividade = atividadeRepository.findById(id);
        if (atividade.isPresent()){
            return new ResponseEntity<>(objectMapper.writeValueAsString(atividade), HttpStatus.OK);
        }else return new ResponseEntity<>("Atividade não encontrada!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> createAtividade(Atividade model){
        if (!atividadeRepository.existsAtividadeByNome(model.getNome())) {
            Atividade atividade = new Atividade();
            atividade.setAtividadeId(model.getAtividadeId());
            atividade.setNome(model.getNome());
            atividade.setOrigem(model.getOrigem());
            atividade.setCliente(model.getCliente());
            atividade.setOperacao(model.getOperacao());
            atividade.setDesenvolvedores(model.getDesenvolvedores());
            atividade.setResponsavel(model.getResponsavel());
            atividade.setStatus(model.getStatus());
            atividade.setTipoAtividade(model.getTipoAtividade());
            atividade.setKeyuser(model.getKeyuser());
            atividade.setGerente(model.getGerente());
            atividade.setPdo(model.getPdo());
            atividade.setDiretor(model.getDiretor());
            atividade.setTimeline(model.getTimeline());
            Atividade atividadeSalvo = atividadeRepository.save(atividade);
            if (atividadeRepository.findById(atividadeSalvo.getAtividadeId()).isPresent())
                return new ResponseEntity<>("Atividade cadastrada com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Já existe uma atividade com esse nome!", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    public ResponseEntity<Object> updateAtividade(Atividade model, AtividadeId id){
        if (atividadeRepository.findById(id).isPresent()){
            Atividade atividade = atividadeRepository.findById(id).get();
            atividade.setNome(model.getNome());
            atividade.setOrigem(model.getOrigem());
            atividade.setCliente(model.getCliente());
            atividade.setOperacao(model.getOperacao());
            atividade.setDesenvolvedores(model.getDesenvolvedores());
            atividade.setResponsavel(model.getResponsavel());
            atividade.setStatus(model.getStatus());
            atividade.setTipoAtividade(model.getTipoAtividade());
            atividade.setKeyuser(model.getKeyuser());
            atividade.setGerente(model.getGerente());
            atividade.setPdo(model.getPdo());
            atividade.setDiretor(model.getDiretor());
            atividade.setTimeline(model.getTimeline());
            atividade.setDtAlteracao(LocalDateTime.now());
            Atividade atividadeSalvo = atividadeRepository.save(atividade);
            if (atividadeRepository.findById(atividadeSalvo.getAtividadeId()).isPresent())
                return new ResponseEntity<>("Atividade atualizada com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Atividade não encontrada!", HttpStatus.NOT_FOUND);
    }
}

package com.coerpa.strategy.api.service;

import com.coerpa.strategy.api.model.Desenvolvedor;
import com.coerpa.strategy.api.repository.DesenvolvedorRepository;
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
public class DesenvolvedorService {
    private final DesenvolvedorRepository desenvolvedorRepository;
    private final ObjectMapper objectMapper;

    public ResponseEntity<Object> getDesenvolvedores() throws JsonProcessingException {
        List<Desenvolvedor> desenvolvedoresList = desenvolvedorRepository.findAll();
        if (!desenvolvedoresList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(desenvolvedoresList), HttpStatus.OK);
        } else return new ResponseEntity<>("Não há desenvolvedores cadastrados!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> getDesenvolvedor(Long id) throws JsonProcessingException {
        Optional<Desenvolvedor> desenvolvedor = desenvolvedorRepository.findById(id);
        if (desenvolvedor.isPresent()){
            return new ResponseEntity<>(objectMapper.writeValueAsString(desenvolvedor), HttpStatus.OK);
        }else return new ResponseEntity<>("Desenvolvedor não encontrado!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> createDesenvolvedor(Desenvolvedor model){
        if (!desenvolvedorRepository.existsDesenvolvedorByNome(model.getNome())) {
            Desenvolvedor desenvolvedor = new Desenvolvedor();
            desenvolvedor.setNome(model.getNome());
            Desenvolvedor desenvolvedorSalvo = desenvolvedorRepository.save(desenvolvedor);
            if (desenvolvedorRepository.findById(desenvolvedorSalvo.getId()).isPresent())
                return new ResponseEntity<>("Desenvolvedor cadastrado com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Já existe um desenvolvedor com esse nome!", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    public ResponseEntity<Object> updateDesenvolvedor(Desenvolvedor model, Long id){
        if (desenvolvedorRepository.findById(id).isPresent()){
            Desenvolvedor desenvolvedor = desenvolvedorRepository.findById(id).get();
            desenvolvedor.setNome(model.getNome());
            desenvolvedor.setAtivo(model.getAtivo());
            desenvolvedor.setDtAlteracao(LocalDateTime.now());
            Desenvolvedor desenvolvedorSalvo = desenvolvedorRepository.save(desenvolvedor);
            if (desenvolvedorRepository.findById(desenvolvedorSalvo.getId()).isPresent())
                return new ResponseEntity<>("Desenvolvedor atualizado com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Desenvolvedor não encontrado!", HttpStatus.NOT_FOUND);
    }
}

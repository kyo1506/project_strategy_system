package com.coerpa.strategy.api.service;

import com.coerpa.strategy.api.model.Cliente;
import com.coerpa.strategy.api.repository.ClienteRepository;
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
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ObjectMapper objectMapper;

    public ResponseEntity<Object> getClientes() throws JsonProcessingException {
        List<Cliente> clientesList = clienteRepository.findAll();
        if (!clientesList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(clientesList), HttpStatus.OK);
        } else return new ResponseEntity<>("Não há clientes cadastrados!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> getCliente(Long id) throws JsonProcessingException {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()){
            return new ResponseEntity<>(objectMapper.writeValueAsString(cliente), HttpStatus.OK);
        }else return new ResponseEntity<>("Cliente não encontrado!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> createCliente(Cliente model){
        if (!clienteRepository.existsClientesByNome(model.getNome())) {
            Cliente cliente = new Cliente();
            cliente.setNome(model.getNome());
            cliente.setSigla(model.getSigla());
            cliente.setOperacoes(model.getOperacoes());
            Cliente clienteSalvo = clienteRepository.save(cliente);
            if (clienteRepository.findById(clienteSalvo.getId()).isPresent())
                return new ResponseEntity<>("Cliente cadastrado com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Já existe um cliente com esse nome!", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    public ResponseEntity<Object> updateCliente(Cliente model, Long id){
        if (clienteRepository.findById(id).isPresent()){
            Cliente cliente = clienteRepository.findById(id).get();
            cliente.setNome(model.getNome());
            cliente.setAtivo(model.getAtivo());
            cliente.setOperacoes(model.getOperacoes());
            cliente.setDtAlteracao(LocalDateTime.now());
            Cliente clienteSalvo = clienteRepository.save(cliente);
            if (clienteRepository.findById(clienteSalvo.getId()).isPresent())
                return new ResponseEntity<>("Cliente atualizado com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Cliente não encontrado!", HttpStatus.NOT_FOUND);
    }
}

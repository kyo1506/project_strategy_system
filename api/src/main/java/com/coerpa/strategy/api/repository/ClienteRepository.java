package com.coerpa.strategy.api.repository;

import com.coerpa.strategy.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsClientesByNome(String nome);
}
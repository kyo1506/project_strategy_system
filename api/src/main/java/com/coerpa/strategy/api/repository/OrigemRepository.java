package com.coerpa.strategy.api.repository;

import com.coerpa.strategy.api.model.Origem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrigemRepository extends JpaRepository<Origem, Long> {
    boolean existsOrigemByNome(String nome);
}
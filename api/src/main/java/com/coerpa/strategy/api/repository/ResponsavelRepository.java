package com.coerpa.strategy.api.repository;

import com.coerpa.strategy.api.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
    boolean existsResponsavelByNome(String nome);
}
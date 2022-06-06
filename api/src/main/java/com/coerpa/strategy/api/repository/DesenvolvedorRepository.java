package com.coerpa.strategy.api.repository;

import com.coerpa.strategy.api.model.Desenvolvedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesenvolvedorRepository extends JpaRepository<Desenvolvedor, Long> {
    boolean existsDesenvolvedorByNome(String nome);
}
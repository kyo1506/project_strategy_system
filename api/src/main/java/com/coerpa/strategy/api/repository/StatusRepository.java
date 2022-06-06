package com.coerpa.strategy.api.repository;

import com.coerpa.strategy.api.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    boolean existsStatusByNome(String nome);
}
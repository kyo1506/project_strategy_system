package com.coerpa.strategy.api.repository;

import com.coerpa.strategy.api.model.TipoAtividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoAtividadeRepository extends JpaRepository<TipoAtividade, Long> {
    boolean existsTipoAtividadeByNome(String nome);
}
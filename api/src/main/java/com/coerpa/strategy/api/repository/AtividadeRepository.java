package com.coerpa.strategy.api.repository;

import com.coerpa.strategy.api.model.Atividade;
import com.coerpa.strategy.api.model.AtividadeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AtividadeRepository extends JpaRepository<Atividade, AtividadeId> {
    @Query("SELECT count(e) FROM Atividade e WHERE e.atividadeId= (:id)")
    Integer countAtividadeById(@Param("id") AtividadeId id);

    boolean existsAtividadeByNome(String nome);
}
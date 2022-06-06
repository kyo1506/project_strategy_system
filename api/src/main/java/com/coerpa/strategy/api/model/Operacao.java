package com.coerpa.strategy.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operacao")
public class Operacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
    @Column(name = "dtInclusao", nullable = false)
    private LocalDateTime dtInclusao = LocalDateTime.now();
    @Column(name = "dtAlteracao")
    private LocalDateTime dtAlteracao;
}

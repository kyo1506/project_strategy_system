package com.coerpa.strategy.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "sigla", nullable = false)
    private String sigla;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Operacao> operacoes;
    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
    @Column(name = "dtInclusao", nullable = false)
    private LocalDateTime dtInclusao = LocalDateTime.now();
    @Column(name = "dtAlteracao")
    private LocalDateTime dtAlteracao;
}

package com.coerpa.strategy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@Table(name = "atividade")
@JsonPropertyOrder({"atividadeId", "nome", "origem", "cliente", "operacao","desenvolvedores",
        "responsavel", "status", "tipoAtividade", "keyuser", "gerente", "pdo", "diretor", "timeline", "dtInclusao", "dtAlteracao"})
public class Atividade implements Serializable{
    @EmbeddedId
    @JsonProperty("atividadeId")
    private AtividadeId atividadeId;

    @Column(name = "nome", nullable = false)
    @JsonProperty("nome")
    private String nome;

    @ManyToOne(targetEntity = Origem.class, fetch = FetchType.LAZY)
    @JsonProperty("origem")
    private Origem origem;

    @ManyToOne(targetEntity = Cliente.class, fetch = FetchType.LAZY)
    @JsonProperty("cliente")
    private Cliente cliente;

    @ManyToOne(targetEntity = Operacao.class, fetch = FetchType.LAZY)
    @JsonProperty("operacao")
    private Operacao operacao;

    @ManyToMany(
            targetEntity = Desenvolvedor.class,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JsonProperty("desenvolvedores")
    private List<Desenvolvedor> desenvolvedores;

    @ManyToOne(targetEntity = Responsavel.class, fetch = FetchType.LAZY)
    @JsonProperty("responsavel")
    private Responsavel responsavel;

    @ManyToOne(targetEntity = Status.class, fetch = FetchType.LAZY)
    @JsonProperty("status")
    private Status status;

    @ManyToOne(targetEntity = TipoAtividade.class, fetch = FetchType.LAZY)
    @JsonProperty("tipoAtividade")
    private TipoAtividade tipoAtividade;

    @Column(name = "keyuser")
    @JsonProperty("keyuser")
    private String keyuser;

    @Column(name = "gerente")
    @JsonProperty("gerente")
    private String gerente;

    @Column(name = "pdo")
    @JsonProperty("pdo")
    private String pdo;

    @Column(name = "diretor")
    @JsonProperty("diretor")
    private String diretor;

    @OneToMany(
            targetEntity = Timeline.class,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JsonProperty("timeline")
    private List<Timeline> timeline;

    @Column(name = "dtInclusao", nullable = false)
    @JsonProperty("dtInclusao")
    private LocalDateTime dtInclusao = LocalDateTime.now();

    @Column(name = "dtAlteracao")
    @JsonProperty("dtAlteracao")
    private LocalDateTime dtAlteracao;
}
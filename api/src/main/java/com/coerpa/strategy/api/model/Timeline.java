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
@Table(name = "timeline")
public class Timeline implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "observacao", nullable = false, updatable = false)
    private String observacao;
    @Column(name = "dtInclusao", nullable = false, updatable = false)
    private LocalDateTime dtInclusao = LocalDateTime.now();
}

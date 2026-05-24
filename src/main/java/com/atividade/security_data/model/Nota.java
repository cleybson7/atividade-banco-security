package com.atividade.security_data.model;

import com.atividade.security_data.util.constants.Prioridade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_nota")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String conteudo;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;
}

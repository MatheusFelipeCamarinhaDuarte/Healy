package br.com.fiap.healy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PESSOA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_TB_PESSOA", columnNames = {
                "EMAIL"
        })
})
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PESSOA")
    @SequenceGenerator(name = "SQ_PESSOA")
    @Column(name = "ID_PESSOA")
    private Long id;

    @Column(name = "NM_PESSOA")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DT_NASCIMENTO")
    private LocalDate nascimento;

    @Column(name = "CPF")
    private String cpf;

    @Enumerated
    @Column(name = "TIPO_PESSOA",nullable = false)
    private Tipo tipoPessoa;
}

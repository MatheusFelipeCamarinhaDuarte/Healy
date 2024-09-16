package br.com.fiap.healy.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_PESSOA", uniqueConstraints = {
        /**
         * UK para garantir que não se tenha mais de uma pessoa com o mesmo e-mail.
         * Uk para garantir que não se tenha mais de uma pessoa com o mesmo CPF.
         */
        @UniqueConstraint(name = "UK_TB_PESSOA_EMAIL", columnNames = {"EMAIL"}),
        @UniqueConstraint(name = "UK_TB_PESSOA_CPF", columnNames = {"CPF"})
})
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "TIPO_PESSOA")
    private Tipo tipoPessoa;
}

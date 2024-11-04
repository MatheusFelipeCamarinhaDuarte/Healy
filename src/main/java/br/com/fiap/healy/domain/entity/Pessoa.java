package br.com.fiap.healy.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_PESSOA")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PESSOA")
    private Long id;

    @NotNull()
    @Column(name = "NM_PESSOA")
    private String nome;
    @NotNull()
    @Column(name = "EMAIL")
    private String email;
    @NotNull()
    @Column(name = "DT_NASCIMENTO")
    private LocalDate nascimento;
    @NotNull()
    @Column(name = "CPF")
    private String cpf;
    @NotNull()
    @Column(name = "TELEFONE")
    private String telefone;



}

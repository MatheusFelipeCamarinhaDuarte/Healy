package br.com.fiap.healy.domain.entity;

import jakarta.persistence.*;
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

    @Column(name = "NM_PESSOA")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DT_NASCIMENTO")
    private LocalDate nascimento;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "TELEFONE")
    private String telefone;



}

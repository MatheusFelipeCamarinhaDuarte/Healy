package br.com.fiap.healy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_HEALY_PESSOA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_TB_HEALY_PESSOA",columnNames = {
                "EMAIL"
        })
})
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_HEALY_PESSOA")
    @SequenceGenerator(name = "SQ_HEALY_PESSOA")
    @Column(name = "ID_PESSOA")
    private Long id;

    @Column(name = "NM_PESSOA")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "DT_NASCIMENTO")
    private LocalDate nasciemento;

}

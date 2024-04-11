package br.com.fiap.healy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_HEALY_PACIENTE")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HEALY_PACIENTE")
    @SequenceGenerator(name = "SQ_HEALY_PACIENTE", sequenceName = "SQ_HEALY_PACIENTE", allocationSize = 1)
    @Column(name = "ID_PACIENTE")
    private Long id;

    @Column(name = "USER_PACIENTE")
    private String userPaciente;

    @Column(name = "SENHA_PACIENTE")
    private String senhaPaciente;

    @Column(name = "CPF")
    private String cpf;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(
                    name = "FK_PACIENTE_PESSOA"
            )
    )
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "PLANO",
            referencedColumnName = "ID_PLANO_SAUDE",
            foreignKey = @ForeignKey(
                    name = "FK_PACIENTE_PLANO_SAUDE"
            )
    )
    private PlanoSaude plano;


}
package br.com.fiap.healy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "TB_HEALY_PROFISSIONAL_SAUDE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_TB_HEALY_PROFISSIONAL_SAUDE_USER", columnNames = {
                "USER_PROFISSIONAL"
        }),
        @UniqueConstraint(name = "UK_TB_HEALY_PROFISSIONAL_SAUDE_DOCUMENTO", columnNames = {
                "DOCUMENTO"
        })
})
public class ProfissionalSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PROFISSIONAL_SAUDE")
    @SequenceGenerator(name = "SQ_PROFISSIONAL_SAUDE", sequenceName = "SQ_PROFISSIONAL_SAUDE", allocationSize = 1)
    @Column(name = "ID_PROFISSIONAL_SAUDE")
    private Long id;

    @Column(name = "USER_PROFISSIONAL")
    private String userMedico;

    @Column(name = "SENHA_PROFISSIONAL")
    private String senhaMedico;

    @Column(name = "DOCUMENTO")
    private String crm;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(
                    name = "FK_PROFISSIONAL_PESSOA"
            )
    )
    private Pessoa pessoa;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "TB_HEALY_PROFISSIONAL_PACIENTE",
            joinColumns = {
                    @JoinColumn(
                            name = "PROFISSIONAL",
                            referencedColumnName = "ID_PROFISSIONAL_SAUDE",
                            foreignKey = @ForeignKey(
                                    name = "FK_PROFISSIONAL_DO_PACIENTE"
                            )
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "PACIENTE",
                            referencedColumnName = "ID_PACIENTE",
                            foreignKey = @ForeignKey(
                                    name = "FK_PACIENTE_DO_PROFISSIONAL"
                            )
                    )
            }

    )
    private Set<Paciente> pacientes = new LinkedHashSet<>();

}

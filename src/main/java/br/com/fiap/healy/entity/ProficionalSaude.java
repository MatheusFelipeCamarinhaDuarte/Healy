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
@Table(name = "TB_HEALY_PROFICIONAL_SAUDE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_TB_HEALY_PROFICIONAL_SAUDE_USER", columnNames = {
                "USER_PROFICIONAL"
        }),
        @UniqueConstraint(name = "UK_TB_HEALY_PROFICIONAL_SAUDE_DOCUMENTO", columnNames = {
                "DOCUMENTO"
        })
})
public class ProficionalSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PROFICIONAL_SAUDE")
    @SequenceGenerator(name = "SQ_PROFICIONAL_SAUDE", sequenceName = "SQ_PROFICIONAL_SAUDE", allocationSize = 1)
    @Column(name = "ID_PROFICIONAL_SAUDE")
    private Long id;

    @Column(name = "USER_PROFICIONAL")
    private String userMedico;

    @Column(name = "SENHA_PROFICIONAL")
    private String senhaMedico;

    @Column(name = "DOCUMENTO")
    private String crm;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(
                    name = "FK_PROFICIONAL_PESSOA"
            )
    )
    private Pessoa pessoa;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "TB_HEALY_PROFICIONAL_PACIENTE",
            joinColumns = {
                    @JoinColumn(
                            name = "PROFICIONAL",
                            referencedColumnName = "ID_PROFICIONAL_SAUDE",
                            foreignKey = @ForeignKey(
                                    name = "FK_PROFICIONAL_DO_PACIENTE"
                            )
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "PACIENTE",
                            referencedColumnName = "ID_PACIENTE",
                            foreignKey = @ForeignKey(
                                    name = "FK_PACIENTE_DO_PROFICIONAL"
                            )
                    )
            }

    )
    private Set<Paciente> paciente = new LinkedHashSet<>();

}

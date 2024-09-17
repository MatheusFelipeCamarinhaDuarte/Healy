package br.com.fiap.healy.domain.entity;

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
@Table(name = "TB_PROFISSIONAL_SAUDE", uniqueConstraints = {
        /**
         * UK para garantir que não exista um médico com o mesmo documento.
         * UK para garantir que não exista um médico que herde uma mesma pessoa.
         */
        @UniqueConstraint(name = "UK_TB_PROFISSIONAL_SAUDE_DOCUMENTO", columnNames = {"DOCUMENTO"}),
        @UniqueConstraint(name = "UK_TB_PROFISSIONAL_SAUDE_PESSOA", columnNames = {"PESSOA"})
})
public class ProfissionalSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROFISSIONAL_SAUDE")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(
            name = "DOCUMENTO",
            referencedColumnName = "ID_DOCUMENTO_SAUDE",
            foreignKey = @ForeignKey(
                    name = "FK_DOCUMENTO_SAUDE_PROFISSIONALL"
            )
    )
    private DocumentoSaude documento;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
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
            name = "TB_PROFISSIONAL_PACIENTE",
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
                            name = "PESSOA",
                            referencedColumnName = "ID_PESSOA",
                            foreignKey = @ForeignKey(
                                    name = "FK_PACIENTE_DO_PROFISSIONAL"
                            )
                    )
            }
    )
    private Set<Pessoa> pacientes = new LinkedHashSet<>();

}

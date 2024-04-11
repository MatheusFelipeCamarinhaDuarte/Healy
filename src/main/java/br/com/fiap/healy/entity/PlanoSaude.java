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
@Table(name = "TB_HEALY_PLANO_SAUDE")
public class PlanoSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_PLANO_SAUDE")
    @SequenceGenerator(name = "SQ_PLANO_SAUDE",sequenceName = "SQ_PLANO_SAUDE",allocationSize = 1)
    @Column(name = "ID_PLANO_SAUDE")
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(
            name = "TB_HEALY_PLANO_AREA_MEDICA",
            joinColumns = {
                    @JoinColumn(
                            name = "PLANO_SAUDE",
                            referencedColumnName = "ID_PLANO_SAUDE",
                            foreignKey = @ForeignKey(
                                    name = "FK_HEALY_PLANO_AREA_MEDICA"
                            )
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "AREA_MEDICA",
                            referencedColumnName = "ID_PASSAGEIRO",
                            foreignKey = @ForeignKey(
                                    name = "FK_AREA_MEDICA_PLANO"
                            )
                    )
            }
    )
    private Set<AreaMedica> areaMedica = new LinkedHashSet<>();

    @Column(name = "VALOR")
    private Float valor;

}

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
@Table(name = "TB_HEALY_AREA_MEDICA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_TB_HEALY_AREA_MEDICA_NOME", columnNames = {
                "NM_AREA_MEDICA"
        })
})
public class AreaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HEALY_AREA_MEDICA")
    @SequenceGenerator(name = "SQ_HEALY_AREA_MEDICA", sequenceName = "SQ_HEALY_AREA_MEDICA", allocationSize = 1)
    @Column(name = "ID_AREA_MEDICA")
    private Long id;

    @Column(name = "NM_AREA_MEDICA")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;
}

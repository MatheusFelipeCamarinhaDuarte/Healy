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
@Table(name = "TB_HEALY_HISTORICO_MEDICO")
public class HistoricoMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HEALY_HISTORICO_MEDICO")
    @SequenceGenerator(name = "SQ_HEALY_HISTORICO_MEDICO", sequenceName = "SQ_HEALY_HISTORICO_MEDICO", allocationSize = 1)
    @Column(name = "ID_HISTORICO_MEDICO")
    private Long id;

    @Column(name = "DOENCAS")
    private String Doencas;

    @Column(name = "DOENCAS_ANTERIORES")
    private String doencasAnteriores;

    @Column(name = "ALERGIAS")
    private String alergias;

    @Column(name = "MEDICAMENTOS")
    private String medicamento;

}

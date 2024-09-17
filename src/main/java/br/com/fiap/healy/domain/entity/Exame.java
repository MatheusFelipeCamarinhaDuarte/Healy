package br.com.fiap.healy.domain.entity;

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
@Table(name = "TB_EXAME")
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HISTORICO_MEDICO")
    private Long id;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "HIST_DIABETES")
    private Boolean histDiabetes;
    @Column(name = "HIST_DOENC_CORONARIA")
    private Boolean histDoencaCoronaria;
    @Column(name = "HIST_DOENC_VASCULAR")
    private Boolean histDoencaVascular;
    @Column(name = "HIST_FUMO")
    private Boolean histFumo;
    @Column(name = "HIST_HIPERTENSAO")
    private Boolean histHipertensao;
    @Column(name = "HIST_DISLIPIDEMIA")
    private Boolean histDislipidemia;
    @Column(name = "HIST_OBESIDADE")
    private Boolean histObesidade;

    @Column(name = "REMED_DISLIPIDEMIA")
    private Boolean remDislipidemia;
    @Column(name = "REMED_DIABETES")
    private Boolean remDiabetes;
    @Column(name = "REMED_HIPERTENSAO")
    private Boolean remHipertensao;
    @Column(name = "REMED_ACEI_ARB")
    private Boolean remACEIeARB;

    @Column(name = "NVL_COLESTEROL")
    private Double nvlColesterol;
    @Column(name = "NVL_CREATINA")
    private Double nvlCreatina;
    @Column(name = "EXAM_EGFRB")
    private Double exameGFRB;
    @Column(name = "PRES_SISTOLICA")
    private Integer presSistolica;
    @Column(name = "PRES_DIASTOLICA")
    private Integer presDiastolica;
    @Column(name = "INDC_MASSA_CORP")
    private Integer indiceMassa;
    @Column(name = "MESES_ATE_CRISE")
    private Integer mesAteCrise;
    @Column(name = "ANOS_ATE_CRISE" )
    private Integer anoAteCrise;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(
            name = "PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(
                    name = "FK_EXAME_PESSOA"
            )
    )
    private Pessoa pessoa;

}

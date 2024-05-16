package br.com.fiap.healy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_TELEFONE", uniqueConstraints = {
        /**
         * UK para garantir que não tenha nenhum telefone exatamente igual.
         */
        @UniqueConstraint(name = "UK_TB_TELEFONE", columnNames = {"DDD","DDI","NUMERO"})
})
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TELEFONE")
    @SequenceGenerator(name = "SQ_TELEFONE", sequenceName = "SQ_TELEFONE", allocationSize = 1)
    @Column(name = "ID_TELEFONE")
    private Long id;
    @Column(name = "DDI")
    private String ddi;
    @Column(name = "DDD")
    private String ddd;
    @Column(name = "NUMERO")
    private String numero;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(
                    name = "FK_TELEFONE_PESSOA"
            )
    )
    private Pessoa pessoa;

}

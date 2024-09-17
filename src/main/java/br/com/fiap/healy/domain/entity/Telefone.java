package br.com.fiap.healy.domain.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TELEFONE")
    private Long id;
    @Column(name = "DDI")
    private String ddi;
    @Column(name = "DDD")
    private String ddd;
    @Column(name = "NUMERO")
    private String numero;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(
            name = "PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(
                    name = "FK_TELEFONE_PESSOA"
            )
    )
    private Pessoa pessoa;

}

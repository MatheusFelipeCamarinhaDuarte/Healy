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
@Table(name = "TB_USUARIO", uniqueConstraints = {
        /**
         * UK para garantir que não se tenha mais usernames iguais.
         * UK para garantir que não se tenha mais de um usuario vinculado a mesma pessoa.
         */
        @UniqueConstraint(name = "UK_USUARIO_USERNAME", columnNames = {"USERNAME"}),
        @UniqueConstraint(name = "UK_USUARIO_PESSOA", columnNames = {"PESSOA"})
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HEALY_USUARIO")
    @SequenceGenerator(name = "SQ_HEALY_USUARIO")
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "USERNAME")
    private String user;

    @Column(name = "SENHA")
    private String senha;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(
                    name = "FK_USUARIO_PESSOA"
            )
    )
    private Pessoa pessoa;

}


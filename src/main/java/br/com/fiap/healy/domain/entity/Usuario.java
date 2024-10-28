package br.com.fiap.healy.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "SENHA")
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_roles_associacao", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(
                    name = "FK_USUARIO_PESSOA"
            )
    )
    private Pessoa pessoa;

}


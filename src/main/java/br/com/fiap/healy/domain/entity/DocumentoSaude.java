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
@Table(name = "TB_DOCUMENTO_SAUDE", uniqueConstraints = {
        /**
         * UK para garantir que não se tenha mais de um documento com todas as informações iguais.
         */
        @UniqueConstraint(name = "UK_TB_DOCUMENTO_SAUDE_UNICO", columnNames = {
                "ESTADO", "SIGLA", "NUMERO"
        })
})
public class DocumentoSaude {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID_DOCUMENTO_SAUDE")
        private Long id;
        @Column(name = "ESTADO")
        private String estado;
        @Column(name = "SIGLA")
        private String sigla;
        @Column(name = "NUMERO")
        private String numero;


}

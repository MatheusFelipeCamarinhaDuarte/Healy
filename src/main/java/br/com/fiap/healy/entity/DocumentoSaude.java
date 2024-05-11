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
@Table
public class DocumentoSaude {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DOCUMENTO_SAUDE")
        @SequenceGenerator(name = "SQ_DOCUMENTO_SAUDE", sequenceName = "SQ_DOCUMENTO_SAUDE", allocationSize = 1)
        @Column(name = "ID_DOCUMENTO_SAUDE")
        private Long id;
        private String estado;
        private String sigla;
        private Integer numero;


}

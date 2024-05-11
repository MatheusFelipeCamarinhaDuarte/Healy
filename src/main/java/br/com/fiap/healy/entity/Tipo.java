package br.com.fiap.healy.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tipo {

    PF( 1L, "Profissional", "PF"),
    PC( 2L, "Paciente", "PC" );

    private Long id;
    private String nome;
    private String sigla;

    @Override
    public String toString() {return String.valueOf(sigla);}

}

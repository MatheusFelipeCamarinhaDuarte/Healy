package br.com.fiap.healy.dto.response;

import br.com.fiap.healy.entity.Tipo;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PessoaResponse(
        Long id,
        String nome,
        String email,
        LocalDate nascimento,
        Tipo tipoPessoa

) {
}

package br.com.fiap.healy.domain.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PessoaResponse(
        Long id,
        String nome,
        String email,
        LocalDate nascimento
) {
}

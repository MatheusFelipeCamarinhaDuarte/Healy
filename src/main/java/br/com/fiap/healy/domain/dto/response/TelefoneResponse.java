package br.com.fiap.healy.domain.dto.response;

import lombok.Builder;

@Builder
public record TelefoneResponse(
        Long id,
        String ddi,
        String ddd,
        String numero,
        PessoaResponse pessoa
) {
}

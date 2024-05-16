package br.com.fiap.healy.domain.dto.response;

import lombok.Builder;

@Builder
public record UsuarioResponse(
        Long id,
        String user,
        PessoaResponse pessoa
) {
}

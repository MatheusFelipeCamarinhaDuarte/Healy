package br.com.fiap.healy.domain.dto.response;

import lombok.Builder;

import java.util.Collection;

@Builder
public record ProfissionalSaudeResponse(
        Long id,
        DocumentoSaudeResponse documento,
        PessoaResponse pessoa,
        Collection<PessoaResponse> pacientes
) {
}

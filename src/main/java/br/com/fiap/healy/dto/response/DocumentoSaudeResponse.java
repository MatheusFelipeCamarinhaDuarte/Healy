package br.com.fiap.healy.dto.response;

import lombok.Builder;

@Builder
public record DocumentoSaudeResponse(
        Long id,
        String estado,
        String sigla,
        String numero
) {
}

package br.com.fiap.healy.dto.response;

import lombok.Builder;

import java.util.Collection;

@Builder
public record PlanoSaudeResponse(
        Long id,
        Collection<AreaMedicaResponse> area,
        float valor
) {
}

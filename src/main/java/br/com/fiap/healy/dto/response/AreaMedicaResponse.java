package br.com.fiap.healy.dto.response;

import lombok.Builder;

@Builder
public record AreaMedicaResponse(
        Long id,
        String nome,
        String descricao
) {
}

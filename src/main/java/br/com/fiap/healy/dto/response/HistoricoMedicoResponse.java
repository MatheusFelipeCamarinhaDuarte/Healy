package br.com.fiap.healy.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record HistoricoMedicoResponse(
        Long id,
        String doencas,
        String doencasAnteriores,
        String alergias,
        String medicamento
) {
}

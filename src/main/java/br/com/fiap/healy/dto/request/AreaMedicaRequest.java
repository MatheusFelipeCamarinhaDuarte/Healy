package br.com.fiap.healy.dto.request;

import jakarta.validation.constraints.NotNull;

public record AreaMedicaRequest(
        @NotNull(message = "O nome é obrigatório")
        String nome,
        @NotNull(message = "A descrição é obrigatória")
        String descricao

) {
}

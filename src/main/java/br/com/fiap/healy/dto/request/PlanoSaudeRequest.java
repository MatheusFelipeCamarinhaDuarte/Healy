package br.com.fiap.healy.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.Collection;

public record PlanoSaudeRequest(
        @NotNull(message = "Os atributos do plano são obrigatórios")
        Collection<AbstractRequest> area,

        @NotNull(message = "O atributo de valor é obrigatório")
        float valor

) {
}

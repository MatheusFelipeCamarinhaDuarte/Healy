package br.com.fiap.healy.domain.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.Collection;

public record ProfissionalSaudeRequest(
        @NotNull(message = "O proficional da saude precisa ter um documento.")
        AbstractRequest documento,

        @NotNull(message = "O proficional da saude precisa ser uma pessoa.")
        AbstractRequest pessoa,

        Collection<AbstractRequest> pacientes
) {
}

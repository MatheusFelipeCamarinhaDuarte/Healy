package br.com.fiap.healy.dto.request;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Collection;

public record ProfissionalSaudeRequest(
        @NotNull(message = "O proficional da saude precisa ter um documento.")
        AbstractRequest documento,

        @NotNull(message = "O proficional da saude precisa ser uma pessoa.")
        AbstractRequest pessoa,

        Collection<AbstractRequest> pacientes
) {
}

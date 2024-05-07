package br.com.fiap.healy.dto.request;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Collection;

public record ProfissionalSaudeRequest(
        @NotNull(message = "O atributo userPaciente não pode ser nulo")
        String userMedico,

        @NotNull(message = "O atributo senhaPaciente não pode ser nulo")
        String senhaMedico,
        @NotNull(message = "O atributo CPF não pode ser nulo")
        String crm,

        AbstractRequest pessoa,

        Collection<AbstractRequest> pacientes
) {
}

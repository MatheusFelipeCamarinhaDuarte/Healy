package br.com.fiap.healy.dto.request;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record PacienteRequest(
        @NotNull(message = "O atributo userPaciente não pode ser nulo")
        String userPaciente,

        @NotNull(message = "O atributo senhaPaciente não pode ser nulo")
        String senhaPaciente,
        @NotNull(message = "O atributo CPF não pode ser nulo")
        @CPF(message = "O formato não corresponde a um CPF")
        String cpf,
        @NotNull(message = "O atributo pessoa não pode ser nulo")
        AbstractRequest pessoa,
        AbstractRequest plano,

        AbstractRequest historico


) {
}

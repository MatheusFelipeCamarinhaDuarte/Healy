package br.com.fiap.healy.dto.response;

import lombok.Builder;

import java.util.Collection;

@Builder
public record ProficionalSaudeResponse(
        Long id,
        String userPaciente,
        String senhaPaciente,
        String crm,
        PessoaResponse pessoa,
        Collection<PacienteResponse> pacientes
) {
}

package br.com.fiap.healy.dto.response;

import lombok.Builder;

import java.util.Collection;

@Builder
public record ProfissionalSaudeResponse(
        // TODO: Ajeitar a classe ProfissionalSaudeResponse para conter as novas modificações
        Long id,
        String userMedico,
        String senhaMedico,
        String crm,
        PessoaResponse pessoa,
        Collection<PacienteResponse> pacientes
) {
}

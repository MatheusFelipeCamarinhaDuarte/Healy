package br.com.fiap.healy.dto.response;

import lombok.Builder;

@Builder
public record PacienteResponse(
        Long id,
        String userPaciente,
        String cpf,
        PessoaResponse pessoa,
        PlanoSaudeResponse plano,
        HistoricoMedicoResponse historico

) {
}

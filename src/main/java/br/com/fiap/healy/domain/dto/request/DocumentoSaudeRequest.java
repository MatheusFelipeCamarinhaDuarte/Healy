package br.com.fiap.healy.domain.dto.request;

import jakarta.validation.constraints.NotNull;

public record DocumentoSaudeRequest(
    @NotNull(message = "O Estado dentro do documento não pode ser nulo.")
    String estado,

    @NotNull(message = "A sigla dentro do documento não pode ser nula, e tem que corresponder a área de atuação.")
    String sigla,

    @NotNull(message = "O documento precisa de um número que valide ele.")
    String numero
) {
}

package br.com.fiap.healy.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TelefoneRequest(
    @Size(min = 1, max = 4, message = "A DDI tem que ser válida e estar entre")
    @NotNull(message = "A DDI tem que ser válida")
    String ddi,
    @Size(min = 1, max = 3, message = "O DDD tem que ser válido e estar entre")
    @NotNull(message = "O DDD tem que ser válido")
    String ddd,

    @Size(min = 8, max = 9, message = "O número exibido precisa estar entre")
    @NotNull(message = "O número é campo obrigatório")
    String numero,
    AbstractRequest pessoa
) {
}

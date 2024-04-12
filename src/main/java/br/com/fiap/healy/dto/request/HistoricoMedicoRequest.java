package br.com.fiap.healy.dto.request;

import jakarta.validation.constraints.NotNull;

public record HistoricoMedicoRequest(
        @NotNull(message = "O atributo Doencas não pode ser vazio")
        String doencas,
        @NotNull(message = "O atributo doencasAnteriores não pode ser vazio")
        String doencasAnteriores,
        @NotNull(message = "O atributo alergias não pode ser vazio")
        String alergias,
        @NotNull(message = "O atributo medicamento não pode ser vazio")
        String medicamento
) {
}

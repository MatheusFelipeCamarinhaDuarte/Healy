package br.com.fiap.healy.dto.response;

import br.com.fiap.healy.dto.request.AbstractRequest;
import lombok.Builder;

@Builder
public record PacienteResponse(
        Long id,
        String userPaciente,
        String senhaPaciente,
        String cpf,
        AbstractRequest pessoa,
        AbstractRequest plano,
        AbstractRequest historico

) {
}

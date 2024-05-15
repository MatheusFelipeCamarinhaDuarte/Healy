package br.com.fiap.healy.dto.response;

import br.com.fiap.healy.entity.Pessoa;
import lombok.Builder;

import java.util.Collection;

@Builder
public record ProfissionalSaudeResponse(
        Long id,
        DocumentoSaudeResponse documento,
        PessoaResponse pessoa,
        Collection<PessoaResponse> pacientes
) {
}

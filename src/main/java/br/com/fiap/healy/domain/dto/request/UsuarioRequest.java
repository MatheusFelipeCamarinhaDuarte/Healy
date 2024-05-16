package br.com.fiap.healy.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsuarioRequest(
        @NotNull(message = "Usuario não pode ser nulo")
        String user,
        @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,16}$", message = "A senha deve conter:\n" +
                "- pelo menos um dígito;\n" +
                "- pelo menos uma letra minúscula;\n" +
                "- pelo menos uma letra maiúscula;\n" +
                "- pelo menos um caractere especial;\n" +
                "- entre 8 e 16 caracteres.")
        @NotNull(message = "A senha é um campo obrigatório")
        String senha,
        @NotNull(message = "É necessário informar a pessoa")
        AbstractRequest pessoa
) {
}

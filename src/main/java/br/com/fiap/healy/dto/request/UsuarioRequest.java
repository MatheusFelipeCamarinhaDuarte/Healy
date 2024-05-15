package br.com.fiap.healy.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsuarioRequest(
        @NotNull(message = "Usuario não pode ser nulo")
        String user,
        @Pattern(regexp = "/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,16}$/", message = "A senha de ve conter: " +
                "deve conter ao menos um dígito;\n" +
                "deve conter ao menos uma letra minúscula;\n" +
                "conter ao menos uma letra maiúscula;\n" +
                "conter ao menos um caractere especial;\n" +
                "conter ao menos 8 dos caracteres mencionados e no máximo 16;\n")
        @NotNull(message = "A senha é um campo obrigatório")
        String senha,
        @NotNull(message = "É necessário informar a pessoa")
        AbstractRequest pessoa
) {
}

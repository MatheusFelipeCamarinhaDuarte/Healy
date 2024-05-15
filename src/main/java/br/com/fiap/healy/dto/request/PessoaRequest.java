package br.com.fiap.healy.dto.request;

import br.com.fiap.healy.entity.Tipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

public record PessoaRequest(
        @NotNull(message = "O nome é obrigatório")
        String nome,

        @Email(message = "Email inválido")
        @NotNull(message = "O atributo email é obrigatório")
        String email,

        @PastOrPresent(message = "A data de nascimento não pode ser no futuro")
        @NotNull(message = "O atributo data de nascimento é obrigatório")
        LocalDate nascimento,

        @NotNull(message = "Informe o tipo da pessoa entre jurídica ou física")
        Tipo tipo,

        // Como tava dando erro, tirei o @CPF
        @NotNull(message = "CPF inválido.")
        String cpf

) {
}

package br.com.wanshitong.wst.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoModel {
    @Email
    @NotBlank(message = "Necessario informar a matricula do solicitante")
    private String email;

    @NotBlank(message = "Necessario informar a matricula do bibliotecario")
    private String bibliotecario;

    @NotNull(message = "O emprestimo deve conter pelo menos um livro")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private Set<String> isbnLivros;
}

package br.com.wanshitong.wst.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroAtualizacaoModel {
    private String imgLivro;

    @NotBlank(message = "O nome do livro nao pode estar vazio")
    @Size(min = 1, message = "O nome do livro deve conter pelo menos {min} caracteres")
    @Size(max = 200, message = "O nome do livro deve conter no maximo {max} caracteres")
    private String nomeLivro;

    @Size(max = 500, message = "A descricao do livro deve conter no maximo {max} caracteres")
    private String descricaoLivro;

    @NotNull(message = "A quantidade nao pode estar vazia")
    @Size(min = 0, message = "A quantidade de estoque nao pode ser menor que 0")
    private Long qtdDisponivel;
}

package br.com.wanshitong.wst.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroCadastroModel {
    @NotBlank(message = "O ISBN nao pode estar vazio")
    @Size(min = 13, max = 13, message = "O ISBN deve ter 13 digitos")
    @Pattern(regexp = "^[0-9]*[1-9][0-9]*$", message = "Deve conter apenas numeros")
    private String isbn;

    private String imgLivro;

    @NotBlank(message = "O nome do livro nao pode estar vazio")
    @Size(min = 1, message = "O nome do livro deve conter pelo menos {min} caracteres")
    @Size(max = 200, message = "O nome do livro deve conter no maximo {max} caracteres")
    private String nomeLivro;

    @Size(max = 500, message = "A descricao do livro deve conter no maximo {max} caracteres")
    private String descricaoLivro;

    @NotNull(message = "E preciso informar o Autor do livro")
    private String idAutor;

    @NotNull(message = "E preciso informar a Editora do livro")
    private String idEditora;

    @NotNull(message = "E preciso informar o Genero do livro")
    private String idGenero;

    @NotNull(message = "A quantidade nao pode estar vazia")
    private Long qtdDisponivel;

    @NotBlank(message = "E preciso informar a matricula do Bibliotecario")
    private String matriculaBibliotecario;
}

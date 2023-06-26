package br.com.wanshitong.wst.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LivroConsultaModel {

//    private Long idLivro;

    @NotBlank(message = "O ISBN do livro deve ser informado")
    private String isbn;
}

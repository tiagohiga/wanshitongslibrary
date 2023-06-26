package br.com.wanshitong.wst.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoLivroModel {
    private String isbn;

    private LocalDate dataDevolucao;
}

package br.com.wanshitong.wst.model;

import br.com.wanshitong.wst.enums.StatusEmprestimoEnum;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmprestimoResponseModel {
    private Long id;

    private String nomeUsuario;

    private String bibliotecario;

    private StatusEmprestimoEnum statusEmprestimo;

    private LocalDate dataEmprestimo;

    private LocalDate prazoDevolucao;

    private Set<EmprestimoLivroModel> livros;
}

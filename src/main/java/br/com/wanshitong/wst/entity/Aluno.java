package br.com.wanshitong.wst.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "TBWST_ALUNO", schema = "WSTS")
public class Aluno {
    @Id
    @Column(name = "ID_ALUNO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAluno;

    @Column(name = "NM_ALUNO")
    private String nomeAluno;

    @Column(name = "QTD_ATRASO")
    private int qtdAtrasos;

    @Column(name = "BLQ_ALUNO")
    private boolean bloqueioAluno;

    @OneToOne
    @JoinColumn(name = "ID_USER_ALN")
    private Usuario usuario;

//    @OneToMany(mappedBy = "aluno")
//    private Set<Emprestimo> emprestimosRealizados;
}

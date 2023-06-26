package br.com.wanshitong.wst.entity;

import br.com.wanshitong.wst.enums.StatusEmprestimoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "emprestimos")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBWST_EMPRESTIMO", schema = "WSTS")
public class Emprestimo {
    @Id
    @Column(name = "ID_EMPRESTIMO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmprestimo;

//    @ManyToOne
//    @JoinColumn(name = "ID_ALUNO")
//    @Column(name = "EMAIL_SOLICITANTE")
//    private String emailSolicitante;

    @ManyToOne
    private Usuario usuario;

    @Column(name = "MATR_BIBLIOTECARIO")
    private String bibliotecario;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_EMPRESTIMO")
    private StatusEmprestimoEnum statusEmprestimo;

    @Column(name = "DT_EMPRESTIMO")
    private LocalDate dataEmprestimo;

    @Column(name = "PRZ_DEVOLUCAO")
    private LocalDate prazoDevolucao;

    @JsonIgnore
    @OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL)
    private Set<LivroEmprestimo> emprestimos = new HashSet<>();

    public void addLivroEmprestimo(LivroEmprestimo livroEmprestimo){
        this.emprestimos.add(livroEmprestimo);
    }
}

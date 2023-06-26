package br.com.wanshitong.wst.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "TBWST_LIVRO", schema = "WSTS")
public class Livro {
    @Id
    @Column(name = "ID_LIVRO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "IMG_LIVRO")
    private String imgLivro;

    @Column(name = "NM_LIVRO")
    private String nomeLivro;

    @Column(name = "DSCR_LIVRO")
    private String descricaoLivro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_AUTOR")
    private Autor idAutor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EDITORA")
    private Editora idEditora;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_GENERO")
    private Genero idGenero;

    @Column(name = "QTD_DISPONIVEL")
    private int qtdDisponivel;

//    @ManyToOne
    @JoinColumn(name = "MATR_BIBLIOTECARIO")
    private String bibliotecario;

    @JsonIgnore
    @OneToMany(mappedBy = "livro")
    private Set<LivroEmprestimo> emprestimos = new HashSet<>();
}

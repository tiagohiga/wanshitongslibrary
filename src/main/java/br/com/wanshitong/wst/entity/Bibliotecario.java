package br.com.wanshitong.wst.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBWST_BIBLIOTECARIO", schema = "WSTS")
public class Bibliotecario {
    @Id
    @Column(name = "ID_BIBLIOTECARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBibliotecario;

    @Column(name = "NM_BIBLIOTECARIO")
    private String nomeBibliotecario;

//    @OneToMany(mappedBy = "bibliotecario")
//    private Set<Livro> livrosCadastrados;

//    @OneToMany(mappedBy = "bibliotecario")
//    private Set<Emprestimo> emprestimosGerados;
}

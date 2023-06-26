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
@Table(name = "TBWST_AUTOR", schema = "WSTS")
public class Autor {
    @Id
    @Column(name = "ID_AUTOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;

    @Column(name = "NM_AUTOR")
    private String nomeAutor;

    @OneToMany(mappedBy = "idAutor")
    private Set<Livro> livros;
}

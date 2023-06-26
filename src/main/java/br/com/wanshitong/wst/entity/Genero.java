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
@Table(name = "TBWST_GENERO", schema = "WSTS")
public class Genero {
    @Id
    @Column(name = "ID_GENERO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGenero;

    @Column(name = "NM_GENERO")
    private String nomeGenero;

    @OneToMany(mappedBy = "idGenero")
    private Set<Livro> livros;
}

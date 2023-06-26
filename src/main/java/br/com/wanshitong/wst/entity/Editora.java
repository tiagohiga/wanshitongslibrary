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
@Table(name = "TBWST_EDITORA", schema = "WSTS")
public class Editora {
    @Id
    @Column(name = "ID_EDITORA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEditora;

    @Column(name = "NM_EDITORA")
    private String nomeEditora;

    @OneToMany(mappedBy = "idEditora")
    private Set<Livro> livros;
}

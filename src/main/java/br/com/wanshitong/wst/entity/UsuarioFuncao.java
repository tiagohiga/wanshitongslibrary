package br.com.wanshitong.wst.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "TBWST_USUARIO_FUNCAO", schema = "WSTS")
public class UsuarioFuncao {
    @Id
    @Column(name = "ID_FUNCAO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncao;

    @Column(name = "NM_FUNCAO")
    private String nomeFuncao;

//    @ManyToOne
//    @JoinColumn(name = "idUsuario")
//    private Usuario usuario;
}

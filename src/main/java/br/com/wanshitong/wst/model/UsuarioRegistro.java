package br.com.wanshitong.wst.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistro {
    private String nomeUsuario;

    private String emailUsuario;

    private String senhaUsuario;

    private int qtdAtrasos;

    private boolean usuarioAtivo;

    private String usuarioFuncao;
}

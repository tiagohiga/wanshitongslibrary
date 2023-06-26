package br.com.wanshitong.wst.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAutenticar {
    private String emailUsuario;

    private String senhaUsuario;
}

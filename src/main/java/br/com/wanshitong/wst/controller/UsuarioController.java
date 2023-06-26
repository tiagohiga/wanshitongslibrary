package br.com.wanshitong.wst.controller;

import br.com.wanshitong.wst.model.UsuarioAutenticar;
import br.com.wanshitong.wst.model.UsuarioRegistro;
import br.com.wanshitong.wst.model.UsuarioResponse;
import br.com.wanshitong.wst.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponse> registrar(@RequestBody UsuarioRegistro usuario){
        return ResponseEntity.ok(usuarioService.registrar(usuario));
    }

    @PostMapping("/autenticar")
    public ResponseEntity<UsuarioResponse> autenticar(@RequestBody UsuarioAutenticar usuario){
        return ResponseEntity.ok(usuarioService.autenticar(usuario));
    }
}

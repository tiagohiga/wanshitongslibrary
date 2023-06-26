package br.com.wanshitong.wst.service;

import br.com.wanshitong.wst.entity.Usuario;
import br.com.wanshitong.wst.enums.UsuarioFuncao;
import br.com.wanshitong.wst.exception.DuplicateRecordException;
import br.com.wanshitong.wst.model.UsuarioAutenticar;
import br.com.wanshitong.wst.model.UsuarioRegistro;
import br.com.wanshitong.wst.model.UsuarioResponse;
import br.com.wanshitong.wst.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public UsuarioResponse registrar(UsuarioRegistro usuarioRegistro) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmailUsuarioIgnoreCase(usuarioRegistro.getEmailUsuario());

        if (usuarioExistente.isPresent()) throw new DuplicateRecordException(usuarioRegistro.getEmailUsuario());

        Usuario usuario = Usuario.builder()
                .nomeUsuario(usuarioRegistro.getNomeUsuario())
                .emailUsuario(usuarioRegistro.getEmailUsuario())
                .senhaUsuario(passwordEncoder.encode(usuarioRegistro.getSenhaUsuario()))
                .qtdAtrasos(0)
                .usuarioAtivo(true)
                .usuarioFuncao(UsuarioFuncao.USUARIO)
                .build();
        usuarioRepository.save(usuario);

        var jwtToken = jwtService.generateToken(usuario);

        return UsuarioResponse.builder()
                .token(jwtToken)
                .build();
    }

    public UsuarioResponse autenticar(UsuarioAutenticar usuarioAutenticar) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuarioAutenticar.getEmailUsuario(),
                        usuarioAutenticar.getSenhaUsuario())
        );

        Usuario usuario = usuarioRepository.findByEmailUsuarioIgnoreCase(usuarioAutenticar.getEmailUsuario())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));

        var jwtToken = jwtService.generateToken(usuario);

        return UsuarioResponse.builder()
                .token(jwtToken)
                .nome(usuario.getNomeUsuario())
                .funcao(usuario.getUsuarioFuncao().toString())
                .email(usuario.getEmailUsuario())
                .build();
    }
}

package br.com.wanshitong.wst.entity;

import br.com.wanshitong.wst.enums.UsuarioFuncao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TBWST_USUARIO", schema = "WSTS")
public class Usuario implements UserDetails {
    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "USER_NOME")
    private String nomeUsuario;

    @Column(name = "USER_EMAIL")
    private String emailUsuario;

    @JsonIgnore
    @Column(name = "USER_PASSWORD")
    private String senhaUsuario;

    @Column(name = "QTD_ATRASO")
    private int qtdAtrasos;

    @Column(name = "USER_ATIVO")
    private boolean usuarioAtivo;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private Set<Emprestimo> emprestimos;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "USER_FUNCAO")
    private UsuarioFuncao usuarioFuncao;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + usuarioFuncao.name()));
    }

    @Override
    public String getPassword() {
        return senhaUsuario;
    }

    @Override
    public String getUsername() {
        return emailUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usuarioAtivo;
    }
}

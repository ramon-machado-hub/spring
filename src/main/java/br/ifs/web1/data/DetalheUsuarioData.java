package br.ifs.web1.data;

import br.ifs.web1.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DetalheUsuarioData implements UserDetails {

    private final Optional<Usuario> usuarioOptional;

    public DetalheUsuarioData(Optional<Usuario> usuarioOptional) {
        this.usuarioOptional = usuarioOptional;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return usuarioOptional.orElse(new Usuario()).getSenhaUsuario();
    }

    @Override
    public String getUsername() {
        return usuarioOptional.orElse(new Usuario()).getLoginUsuario();
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
        return true;
    }
}

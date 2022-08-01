package br.ifs.web1.service;

import br.ifs.web1.data.DetalheUsuarioData;
import br.ifs.web1.model.Usuario;
import br.ifs.web1.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    public final UsuarioRepository repository;
    public final UsuarioService usuarioService;

    public DetalheUsuarioServiceImpl(UsuarioRepository repository, UsuarioService usuarioService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByLoginUsuario(username);

        if (usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuário ["+username+"] não encontrado");
        }

        return new DetalheUsuarioData(usuario);
    }

}

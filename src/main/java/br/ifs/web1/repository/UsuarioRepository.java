package br.ifs.web1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ifs.web1.model.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    List<Usuario> findByStatusUsuario(String status);

    Usuario findByLoginUsuarioAndSenhaUsuario(String email, String senha);

    Usuario findByTokenUsuario(String token);

    Optional<Usuario> findByLoginUsuario(String login);
}

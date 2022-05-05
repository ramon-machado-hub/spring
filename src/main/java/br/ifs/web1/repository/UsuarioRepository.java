package br.ifs.web1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ifs.web1.model.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
    List<Usuario> findByStatusUsuario(String status);
    Usuario findByEmailUsuario(String email);
    Usuario findByloginUsuarioAndSenhaUsuario(String email, String senha);
    Usuario findById(int id);
}
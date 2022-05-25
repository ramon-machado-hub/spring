package br.ifs.web1.repository;
import br.ifs.web1.model.Perfil;
import br.ifs.web1.model.PerfilUsuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilUsuarioRepository extends CrudRepository<PerfilUsuario, Integer> {
    Perfil getPerfilByIdUsuario(int id);
}

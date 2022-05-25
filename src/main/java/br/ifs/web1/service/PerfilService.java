package br.ifs.web1.service;

import br.ifs.web1.dto.PerfilDto;
import br.ifs.web1.model.Perfil;
import br.ifs.web1.model.Usuario;
import br.ifs.web1.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService extends BaseService{

    @Autowired
    private PerfilRepository perfilRepository;

    public List<Perfil> listar(){
        return (List<Perfil>) perfilRepository.findAll();
    }

    public void create(PerfilDto perfil, String token) throws Exception {
        if (perfil == null){
            throw new Exception("Perfil = null");
        } else if (perfil.getNome_perfil()== null) {
            throw new Exception("Nome: campo nulo");
        }
        Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(token));
        if(opUsu.isPresent()){
                criarLog(opUsu.get().getIdUsuario(),"create_perfil");
                perfilRepository.save(perfil.toPerfil());
        }
    }

}

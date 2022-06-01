package br.ifs.web1.service;

import br.ifs.web1.dto.PerfilDto;
import br.ifs.web1.dto.RuntimeDto;
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

    @Autowired
    RuntimeService runtimeService;

    public List<Perfil> listar(){
        return (List<Perfil>) perfilRepository.findAll();
    }

    public void create(PerfilDto perfil) throws Exception {
        if (perfil == null){
            throw new Exception("Perfil = null");
        } else if (perfil.getNome_perfil()== null) {
            throw new Exception("Nome: campo nulo");
        } if (perfil.getToken().trim().isEmpty()){
            throw new Exception("Token não pode ser em branco");
        }
        Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(perfil.getToken()));
        if(opUsu.isPresent()){
            RuntimeDto runtimeDto = new RuntimeDto();
            runtimeDto.setToken(perfil.getToken());
            runtimeDto.setUrl("localhost:8080/perfil/createperfil");
            if(runtimeService.validar(runtimeDto,opUsu.get().getIdUsuario())) {
                criarLog(opUsu.get().getIdUsuario(), "create_perfil");
                perfilRepository.save(perfil.toPerfil());
            } else {
                throw  new Exception("Usuário não possui permissão para essa transação");
            }

        } else {
            throw new Exception("Token não encontrado");
        }

    }

    public void updatePerfil (PerfilDto perfil) throws Exception{
        if (perfil == null){
            throw new Exception("Perfil = null");
        } else if (perfil.getNome_perfil()== null) {
            throw new Exception("Nome: campo nulo");
        } if (perfil.getToken().trim().isEmpty()){
            throw new Exception("Token não pode ser em branco");
        }

    }

}

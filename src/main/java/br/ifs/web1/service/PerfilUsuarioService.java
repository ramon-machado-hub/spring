package br.ifs.web1.service;

import br.ifs.web1.dto.PerfilUsuarioDto;
import br.ifs.web1.dto.RuntimeDto;
import br.ifs.web1.model.Perfil;
import br.ifs.web1.model.Usuario;
import br.ifs.web1.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilUsuarioService extends  BaseService{

    @Autowired
    PerfilUsuarioRepository perfilUsuarioRepository;

    @Autowired
    RuntimeService runtimeService;

    public void create(PerfilUsuarioDto perfilUsuarioDto) throws Exception{
        if (perfilUsuarioDto == null){
            throw new Exception("perfilUsuario não pode ser nulo");
        }else if (perfilUsuarioDto.getId_perfil()==0){
            throw new Exception("idPerfil = 0");
        } else if(perfilUsuarioDto.getId_usuario()==0){
            throw new Exception("idUsuario = 0");
        } else if (perfilUsuarioDto.getData_inicio().toString().isEmpty()){
            throw new Exception("Data inicio nao pode ser vazio");
        }

        Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(perfilUsuarioDto.getToken()));
        if(opUsu.isPresent()){
            RuntimeDto runtimeDto = new RuntimeDto();
            runtimeDto.setToken(perfilUsuarioDto.getToken());
            runtimeDto.setUrl("localhost:8080/perfilusuario/createperfilusuario");
            if (runtimeService.validar(runtimeDto, opUsu.get().getIdUsuario())){
                criarLog(perfilUsuarioDto.getId_usuario(),"create_perfil_usuario");
                perfilUsuarioRepository.save(perfilUsuarioDto.toPerfilUsuario());
            } else {
                throw new Exception("Usuário não tem permissão para essa transação");
            }

        } else {
            throw new Exception("Token inválido");
        }
    }

    public Perfil getPerfilByIdUsuario(int id){
        return perfilUsuarioRepository.getPerfilByIdUsuario(id);
    }
}

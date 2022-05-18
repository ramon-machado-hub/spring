package br.ifs.web1.service;

import br.ifs.web1.dto.SistemaDto;
import br.ifs.web1.model.Usuario;
import br.ifs.web1.repository.SistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SistemaService extends BaseService{
    @Autowired
    SistemaRepository sistemaRepository;

    public void create(SistemaDto sistema) throws Exception{
        if (sistema == null){
            throw new Exception("sistema não pode ser nulo");
        } else if (sistema.getNome_sistema().isEmpty()){
            throw new Exception("Nome sistema não pode ser um campo em branco");
        } else if (sistema.getStatus_sistema().isEmpty()){
            throw new Exception("Status não pode ser um campo em branco");
        }

        Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(sistema.getToken()));
        if (opUsu.isPresent()){
            criarLog(opUsu.get().getIdUsuario(),"create_sistema");
            sistemaRepository.save(sistema.toSistema());
        }
    }

}

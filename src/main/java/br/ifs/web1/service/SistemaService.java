package br.ifs.web1.service;

import br.ifs.web1.dto.RuntimeDto;
import br.ifs.web1.dto.SistemaDto;
import br.ifs.web1.dto.TokenDto;
import br.ifs.web1.model.Servico;
import br.ifs.web1.model.Sistema;
import br.ifs.web1.model.Usuario;
import br.ifs.web1.repository.SistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SistemaService extends BaseService{
    @Autowired
    SistemaRepository sistemaRepository;

    @Autowired
    private RuntimeService runtimeService;



    public List<Sistema> getAllSistema(String token) throws Exception {
        Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(token));
        if (opUsu.isPresent()){
            RuntimeDto runtimeDto = new RuntimeDto();
            runtimeDto.setToken(token);
            runtimeDto.setUrl("localhost:8080/sistema/getsistemas");
            if (runtimeService.validar(runtimeDto,opUsu.get().getIdUsuario())) {
                System.out.println("validou getsistemas");
                return sistemaRepository.findAll();
            } else {
                System.out.println("não validou runtimeService");
                throw new Exception("Usuário naao encontrado");
            }
        } else {
            System.out.println("não validou token");
            throw new Exception("Usuário naao encontrado");
        }
    }


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
            RuntimeDto runtimeDto = new RuntimeDto();
            runtimeDto.setToken(sistema.getToken());
            runtimeDto.setUrl("localhost:8080/sistema/createsistema");
            if (runtimeService.validar(runtimeDto,opUsu.get().getIdUsuario())){
                criarLog(opUsu.get().getIdUsuario(),"create_sistema");
                sistemaRepository.save(sistema.toSistema());
            } else {
                throw new Exception("Usuário não tem permissão para essa transação");
            }

        } else {
            throw new Exception("token inválido");
        }
    }

    public void updateSistema(SistemaDto sistema) throws Exception{
        if (sistema == null){
            throw new Exception("sistema não pode ser nulo");
        } else if (sistema.getNome_sistema().isEmpty()){
            throw new Exception("Nome sistema não pode ser um campo em branco");
        } else if (sistema.getStatus_sistema().isEmpty()){
            throw new Exception("Status não pode ser um campo em branco");
        }
        Optional <Sistema> opSistema = sistemaRepository.findById(sistema.getId_sistema());
        if (opSistema.isPresent()){
            Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(sistema.getToken()));
            if (opUsu.isPresent()) {
                RuntimeDto runtimeDto = new RuntimeDto();
                runtimeDto.setToken(sistema.getToken());
                runtimeDto.setUrl("localhost:8080/sistema/updatesistema");
                if (runtimeService.validar(runtimeDto,opUsu.get().getIdUsuario())){
                    criarLog(opUsu.get().getIdUsuario(),"update_sistema");
                    sistemaRepository.save(sistema.toSistema());
                } else {
                    throw new Exception("Usuário não tem permissão para essa transação");
                }
            }
        } else {
            throw new Exception("Sistema não encontrado");
        }

    }
}

package br.ifs.web1.service;

import br.ifs.web1.dto.ServicoDto;
import br.ifs.web1.model.Servico;
import br.ifs.web1.model.Usuario;
import br.ifs.web1.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicoService extends BaseService{

    @Autowired
    ServicoRepository servicoRepository;

    public void create(ServicoDto servico) throws Exception{
        if(servico == null){
            throw new Exception("Serviço não pode ser nulo");
        } else if (servico.getNome_servico().isEmpty()){
            throw new Exception("Nome: campo vazio");
        } else if(servico.getStatus_servico().isEmpty()){
            throw new Exception("Status: campo vazio");
        } else if(servico.getUrl_servico().isEmpty()){
            throw new Exception("Url: campo vazio");
        } else if(servico.getId_sistema()==0){
            throw new Exception("Id do Sistema não pode ser 0");
        }
        Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(servico.getToken()));
        if (opUsu.isPresent()){
            criarLog(opUsu.get().getIdUsuario(),"create_serviço");
            servicoRepository.save(servico.toServico());
        }
    }

    public void update(ServicoDto servicoDto){

        Optional <Servico> opServico = servicoRepository.findById(servicoDto.getId_servico());

        if(opServico.isPresent()){
            Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(servicoDto.getToken()));
            if (opUsu.isPresent()){
                servicoRepository.save(servicoDto.toServico());
            }

        }
    }
//
//    public void update(ServicoDto servico){
//        Optional <Servico> opServico = servicoRepository.findById(servico.getId_servico());
//
//        if (opServico.isPresent()){
//            Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(servico.getToken()));
//            Servico servicoBD = opServico.get();
//            servicoBD.setNomeServico(servico.getNome_servico());
//            servicoBD.setStatusServico(servico.getStatus_servico());
//            servicoBD.setUrlServico(servico.getUrl_servico());
//            servicoBD.setIdSistema(servico.getId_sistema());
//            criarLog(opUsu.get().getIdUsuario(),"update_servico");
//            servicoRepository.save(servicoBD);
//        }
//    }

}

package br.ifs.web1.service;

import br.ifs.web1.dto.RuntimeDto;
import br.ifs.web1.dto.ServicoDto;
import br.ifs.web1.dto.TokenDto;
import br.ifs.web1.model.Servico;
import br.ifs.web1.model.Transacao;
import br.ifs.web1.model.Usuario;
import br.ifs.web1.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService extends BaseService{

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    RuntimeService runtimeService;

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
            RuntimeDto runtimeDto = new RuntimeDto();
            runtimeDto.setToken(servico.getToken());
            runtimeDto.setUrl("localhost:8080/servico/createservico");
            if (runtimeService.validar(runtimeDto,opUsu.get().getIdUsuario())) {
                criarLog(opUsu.get().getIdUsuario(), "create_serviço");
                servicoRepository.save(servico.toServico());
            } else {
                throw new Exception("Usuário não tem permissão para essa transação");
            }
        } else {
            throw new Exception("Token inválido");
        }
    }

    public List<Servico> findAllServico(TokenDto tokenDto) throws Exception{
        Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(tokenDto.getToken()));
        if(opUsu.isPresent()){
            RuntimeDto runtimeDto = new RuntimeDto();
            runtimeDto.setToken(tokenDto.getToken());
            runtimeDto.setUrl("localhost:8080/servico/getservicos");
            if (runtimeService.validar(runtimeDto,opUsu.get().getIdUsuario())){
                criarLog(opUsu.get().getIdUsuario(),"getservicos");
                return (List<Servico>) servicoRepository.findAll();
            } else {
                throw new Exception("Usuário não tem permissão para essa transação");
            }
        } else {
            throw new Exception("Token inválido");
        }
    }

    /*
        public List<Transacao> findAllTransacao(TokenDto tokenDto) throws Exception{
        Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(tokenDto.getToken()));
        if (opUsu.isPresent()){
            RuntimeDto runtimeDto = new RuntimeDto();
            runtimeDto.setToken(tokenDto.getToken());
            runtimeDto.setUrl("localhost:8080/transacao/getall");
            if (runtimeService.validar(runtimeDto,opUsu.get().getIdUsuario())){
                criarLog(opUsu.get().getIdUsuario(),"get_all_transação");
                return (List<Transacao>) transacaoRepository.findAll();
            } else {
                throw new Exception("Usuário não tem permissão para essa transação");
            }
        } else {
            throw new Exception("Token inválido");
        }
//        return (List<Transacao>) transacaoRepository.findAll();

    }
     */

    public void update(ServicoDto servicoDto) throws Exception{
        if(servicoDto == null){
            throw new Exception("Serviço não pode ser nulo");
        } else if (servicoDto.getNome_servico().isEmpty()){
            throw new Exception("Nome: campo vazio");
        } else if(servicoDto.getStatus_servico().isEmpty()){
            throw new Exception("Status: campo vazio");
        } else if(servicoDto.getUrl_servico().isEmpty()){
            throw new Exception("Url: campo vazio");
        } else if(servicoDto.getId_sistema()==0){
            throw new Exception("Id do Sistema não pode ser 0");
        }
        Optional <Servico> opServico = servicoRepository.findById(servicoDto.getId_servico());

        if(opServico.isPresent()){
            Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(servicoDto.getToken()));
            if (opUsu.isPresent()){
                RuntimeDto runtimeDto = new RuntimeDto();
                runtimeDto.setToken(servicoDto.getToken());
                runtimeDto.setUrl("localhost:8080/servico/updateservico");
                if (runtimeService.validar(runtimeDto,opUsu.get().getIdUsuario())) {
                    criarLog(opUsu.get().getIdUsuario(), "update_serviço");
                    servicoRepository.save(servicoDto.toServico());
                }else {
                    throw new Exception("Usuário não tem permissão para essa transação");
                }
            } else {
                throw new Exception("Token inválido");
            }
        } else {
            throw new Exception("Serviço não encontrado");
        }
    }
}

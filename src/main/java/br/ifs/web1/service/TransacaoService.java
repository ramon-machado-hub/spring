package br.ifs.web1.service;

import br.ifs.web1.dto.RuntimeDto;
import br.ifs.web1.dto.TokenDto;
import br.ifs.web1.dto.TransacaoDto;
import br.ifs.web1.model.PerfilTransacao;
import br.ifs.web1.model.Transacao;
import br.ifs.web1.model.Usuario;
import br.ifs.web1.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService extends BaseService{

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    RuntimeService runtimeService;

    public void create(TransacaoDto transacaoDto)throws Exception{
        if (transacaoDto == null){
            throw new Exception("transacaoDto não pode ser nulo");
        } else if (transacaoDto.getNome_transacao().trim().isEmpty()){
            throw new Exception("Nome Transacao não pode ser um campo em branco");
        }else if (transacaoDto.getStatus_transacao().trim().isEmpty()){
            throw new Exception("Status Transacao não pode ser um campo em branco");
        }else if (transacaoDto.getUrl_transacao().trim().isEmpty()){
            throw new Exception("url Transacao não pode ser um campo em branco");
        }
        Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(transacaoDto.getToken()));
        if (opUsu.isPresent()) {
            RuntimeDto runtimeDto = new RuntimeDto();
            runtimeDto.setToken(transacaoDto.getToken());
            runtimeDto.setUrl("localhost:8080/transacao/createtransacao");

            if (runtimeService.validar(runtimeDto, opUsu.get().getIdUsuario())){
                criarLog(opUsu.get().getIdUsuario(), "create_transacao");
                transacaoRepository.save(transacaoDto.toTransacao());
            } else {
                throw new Exception("Usuário não tem permissão para essa transação");
            }

        } else {
            throw new Exception("Token inválido");
        }
    }

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

    public void update (TransacaoDto transacaoDto) throws Exception {
        if (transacaoDto == null){
            throw new Exception("transacaoDto não pode ser nulo");
        } else if (transacaoDto.getNome_transacao().trim().isEmpty()){
            throw new Exception("Nome Transacao não pode ser um campo em branco");
        }else if (transacaoDto.getStatus_transacao().trim().isEmpty()){
            throw new Exception("Status Transacao não pode ser um campo em branco");
        }else if (transacaoDto.getUrl_transacao().trim().isEmpty()){
            throw new Exception("url Transacao não pode ser um campo em branco");
        }
        Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(transacaoDto.getToken()));


    }
}

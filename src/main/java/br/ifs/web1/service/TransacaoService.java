package br.ifs.web1.service;

import br.ifs.web1.dto.TransacaoDto;
import br.ifs.web1.model.Usuario;
import br.ifs.web1.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransacaoService extends BaseService{

    @Autowired
    TransacaoRepository transacaoRepository;

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
            criarLog(opUsu.get().getIdUsuario(), "create_sistema");
            transacaoRepository.save(transacaoDto.toTransacao());
        }
    }
}

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

    public void create(TransacaoDto transacao) throws Exception{
        if (transacao == null){
            throw new Exception("Transação não pode ser nulo");
        } else if(transacao.getNome_transacao().isEmpty()){
            throw new Exception("Nome: campo em branco");
        }else if(transacao.getStatus_transacao().isEmpty()){
            throw new Exception("Status: campo em branco");
        }else if(transacao.getUrl_transacao().isEmpty()){
            throw new Exception("URL: campo em branco");
        }else if(transacao.getId_servico()==0){
            throw new Exception("ID serviço: valor deve ser diferente de 0");
        }
        Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(transacao.getToken()));
        if (opUsu.isPresent()){
            criarLog(opUsu.get().getIdUsuario(),"create_transacao");
            transacaoRepository.save(transacao.toTransacao());
        }
    }
}

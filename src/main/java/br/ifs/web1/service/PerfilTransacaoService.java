package br.ifs.web1.service;

import br.ifs.web1.dto.PerfilTransacaoDto;
import br.ifs.web1.model.PerfilTransacao;
import br.ifs.web1.model.Usuario;
import br.ifs.web1.repository.PerfilTransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilTransacaoService extends BaseService{

    @Autowired
    PerfilTransacaoRepository perfilTransacaoRepository;

    public List<PerfilTransacao> findAllPerTran(){
        return (List<PerfilTransacao>) perfilTransacaoRepository.findAll();
    }

    public void create(PerfilTransacaoDto perfilTransacao) throws Exception{
        if(perfilTransacao== null){
            throw new Exception("PerfilTransacao = null");
        } else if (perfilTransacao.getIdTransacao()==0){
            throw new Exception("IdTransacao: 0");
        } else if (perfilTransacao.getIdPerfil()==0){
            throw new Exception("IdPerfil: 0");
        }
        Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(perfilTransacao.getToken()));
        if (opUsu.isPresent()){
            criarLog(opUsu.get().getIdUsuario(),"create_perfil_transacao");
            perfilTransacaoRepository.save(perfilTransacao.toPerfilTransacao());
        } else{
            throw new Exception("Usuario não encontrado, token inválido.");
        }
    }
}

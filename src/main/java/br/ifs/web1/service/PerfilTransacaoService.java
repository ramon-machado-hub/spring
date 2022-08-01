package br.ifs.web1.service;

import br.ifs.web1.dto.PerfilTransacaoDto;
import br.ifs.web1.dto.RuntimeDto;
import br.ifs.web1.dto.TokenDto;
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

    @Autowired
    RuntimeService runtimeService;

    public List<PerfilTransacao> findAllPerTran(TokenDto tokenDto) throws Exception{
        Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(tokenDto.getToken()));
        if (opUsu.isPresent()){
            System.out.println("token1111");
            RuntimeDto runtimeDto = new RuntimeDto();
            runtimeDto.setToken(tokenDto.getToken());
            runtimeDto.setUrl("localhost:8080/perfiltransacao/getall");
            if (runtimeService.validar(runtimeDto,opUsu.get().getIdUsuario())) {
                System.out.println("entrou");
                criarLog(opUsu.get().getIdUsuario(),"get_all_perfiltransacao");
                return (List<PerfilTransacao>) perfilTransacaoRepository.findAll();

            } else {
                throw new Exception("Usuário não tem permissão para essa transação");
            }

        } else {
            System.out.println("token");
            throw new Exception("Token inválido");
        }
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
            RuntimeDto runtimeDto = new RuntimeDto();
            runtimeDto.setToken(perfilTransacao.getToken());
            runtimeDto.setUrl("localhost:8080/perfiltransacao/createperfiltransacao");
            if (runtimeService.validar(runtimeDto,opUsu.get().getIdUsuario())){
                criarLog(opUsu.get().getIdUsuario(),"create_perfil_transacao");
                perfilTransacaoRepository.save(perfilTransacao.toPerfilTransacao());
            } else{
                throw new Exception("Usuário não tem permissão para essa transação.");
            }

        } else{
            throw new Exception("Usuario não encontrado, token inválido.");
        }
    }
}

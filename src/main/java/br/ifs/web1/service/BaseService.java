package br.ifs.web1.service;

import br.ifs.web1.model.LogSystem;
import br.ifs.web1.model.Usuario;
import br.ifs.web1.repository.LogRepository;
import br.ifs.web1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class BaseService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //método global que cria o log com cada ação (inserir, alterar, remover) no banco de dados;
    public void criarLog(int idUsuario, String log){
        Date hoje = new Date();
        Date dataCriacao = new Date(hoje.getTime());
        logRepository.save(LogSystem.builder().idUsuario(idUsuario).logTxt(log).dateLog(dataCriacao.toString()).build()) ;
    }



    //método que retorna o Usuario pelo token
    public Usuario getUsuarioByToken(String token) {
            Optional<Usuario> opUsu = Optional.ofNullable(usuarioRepository.findByTokenUsuario(token));
            System.out.println(token);
            if (opUsu.isPresent()) {
                System.out.println("presente");
                return opUsu.get();
            }
              System.out.println("ausente");
            return null;
    }
}

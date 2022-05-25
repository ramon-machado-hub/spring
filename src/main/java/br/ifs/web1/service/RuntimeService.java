package br.ifs.web1.service;

import br.ifs.web1.dto.RuntimeDto;
import br.ifs.web1.model.Transacao;
import br.ifs.web1.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuntimeService extends BaseService{

    @Autowired
    TransacaoRepository transacaoRepository;


    public boolean validar(RuntimeDto runtimeDto,int idUsuario) {
        try {
            System.out.println("entroou");
            System.out.println("url = "+runtimeDto.getUrl());
            System.out.println(idUsuario);
            List<Transacao> list = transacaoRepository.getByIdUsuarioAndUrl(idUsuario, runtimeDto.getUrl());
            if (list.isEmpty()){
                System.out.println("lista vazia");
                return false;
            }else {
                System.out.println("lista com itens");
            }
            for (Transacao item : list){
                System.out.println("item"+item.getUrlTransacao());
                System.out.println("runtime"+runtimeDto.getUrl());
                System.out.println(item.getUrlTransacao().equals(runtimeDto.getUrl()));

                if (item.getUrlTransacao().equals(runtimeDto.getUrl())){
                    return true;
                }
            }
             return false;
        } catch (Exception e){

        }
//        Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(runtimeDto.getToken()));
//        if (opUsu.isPresent()) {
//            List<Transacao> list = transacaoRepository.getByIdUsuarioAndUrl(opUsu.get().getIdUsuario(), runtimeDto.getUrl());
//            System.out.println("entrou");
//            if (list.contains(runtimeDto.getUrl())) {
//                return true;
//            } else {
//                return false;
//            }
//        }
        return false;
    }
}

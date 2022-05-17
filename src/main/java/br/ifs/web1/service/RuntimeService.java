package br.ifs.web1.service;

import br.ifs.web1.dto.UsuarioDto;
import org.springframework.stereotype.Service;

@Service
public class RuntimeService {

    public boolean validar(String token, String url){
            if (token.trim().isEmpty()){
                return false;
            }
           return  true;
    }
}

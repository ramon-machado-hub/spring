package br.ifs.web1.controller;

import br.ifs.web1.dto.PerfilUsuarioDto;
import br.ifs.web1.dto.TokenDto;
import br.ifs.web1.service.PerfilUsuarioService;
import br.ifs.web1.util.ResponseDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/perfilusuario")
public class PerfilUsuarioController {
    @Autowired
    PerfilUsuarioService perfilUsuarioService;

    @PostMapping(value = "/createperfilusuario")
    public Object criarPerfilUsuario(@RequestBody PerfilUsuarioDto perfilUsuarioDto){
        ResponseDefault response = new ResponseDefault();
        try {
            perfilUsuarioService.create(perfilUsuarioDto);
            response.setValue(true);
            response.setCodigo(200);
        } catch (Exception e){
            response.setCodigo(400);
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setMensagem(e.getMessage());
            response.setValue(false);
        }
        return response;
    }

    @PostMapping(value = "/getall")
    public Object getPerfilUsuario(@RequestBody TokenDto tokenDto){
        ResponseDefault response = new ResponseDefault();
        try {
           return perfilUsuarioService.getAllPerfilUsuario(tokenDto);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return response;
        }

    }


}

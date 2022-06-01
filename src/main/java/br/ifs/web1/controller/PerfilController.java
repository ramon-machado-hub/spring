package br.ifs.web1.controller;

import br.ifs.web1.dto.PerfilDto;
import br.ifs.web1.model.Perfil;
import br.ifs.web1.service.PerfilService;
import br.ifs.web1.util.ResponseDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    PerfilService perfilService;

    @GetMapping(value = "/getAllPerfil")
    public List<Perfil> listar(){
        return perfilService.listar();
    }

    @PostMapping(value = "/createperfil")
    public Object criarPerfil(@RequestBody PerfilDto perfil){
        ResponseDefault response = new ResponseDefault();
        try {
            perfilService.create(perfil);
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
}

package br.ifs.web1.controller;

import br.ifs.web1.dto.SistemaDto;
import br.ifs.web1.service.SistemaService;
import br.ifs.web1.util.ResponseDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sistema")
public class SistemaController {
    @Autowired
    SistemaService sistemaService;

    @PostMapping(value = "/createsistema")
    public Object criarUsuario(@RequestBody SistemaDto sistema) {
        ResponseDefault response = new ResponseDefault();
        try {
            sistemaService.create(sistema);
            response.setValue(true);
            response.setCodigo(200);
        } catch (Exception e) {
            response.setCodigo(400);
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setMensagem(e.getMessage());
            response.setValue(false);
        }
        return response;
    }
}

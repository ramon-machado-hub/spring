package br.ifs.web1.controller;

import br.ifs.web1.dto.ServicoDto;
import br.ifs.web1.service.ServicoService;
import br.ifs.web1.util.ResponseDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servico")
public class ServicoController {
    @Autowired
    ServicoService servicoService;

    @PostMapping(value = "/createservico")
    public Object criarServico(@RequestBody ServicoDto servico) {
        ResponseDefault response = new ResponseDefault();
        try {
            servicoService.create(servico);
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

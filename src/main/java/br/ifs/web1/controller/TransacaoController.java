package br.ifs.web1.controller;

import br.ifs.web1.dto.TransacaoDto;
import br.ifs.web1.service.TransacaoService;
import br.ifs.web1.util.ResponseDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @PostMapping(value = "/createtransacao")
    public Object criarTransacao(TransacaoDto transacao){
        ResponseDefault response = new ResponseDefault();
        try {
            transacaoService.create(transacao);
            response.setValue(true);
            response.setCodigo(200);
        }catch (Exception e) {
            response.setCodigo(400);
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setMensagem(e.getMessage());
            response.setValue(false);
        }
        return response;
    }
}

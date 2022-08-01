package br.ifs.web1.controller;

import br.ifs.web1.dto.TokenDto;
import br.ifs.web1.dto.TransacaoDto;
import br.ifs.web1.model.PerfilTransacao;
import br.ifs.web1.model.Transacao;
import br.ifs.web1.service.TransacaoService;
import br.ifs.web1.util.ResponseDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    @Autowired
    TransacaoService transacaoService;


    @GetMapping(value = "/getAll")
    public Object listar(@RequestBody TokenDto tokenDto){
        ResponseDefault response = new ResponseDefault();
        try {
            return transacaoService.findAllTransacao(tokenDto);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return response;
        }
    }

    @PostMapping(value = "/gettransacoes")
    public Object listarTransacoes(@RequestBody TokenDto tokenDto){
        ResponseDefault response = new ResponseDefault();
        try {
            return transacaoService.findAllTransacao(tokenDto);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return response;
        }


    }

    @PostMapping(value = "/createtransacao")
    public Object criarTransacao(@RequestBody TransacaoDto transacaoDto){
        ResponseDefault response = new ResponseDefault();
        try{
            transacaoService.create(transacaoDto);
            response.setValue(true);
            response.setCodigo(200);
        }catch (Exception e){
            response.setCodigo(400);
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setMensagem(e.getMessage());
            response.setValue(false);
        }
        return response;
    }
}

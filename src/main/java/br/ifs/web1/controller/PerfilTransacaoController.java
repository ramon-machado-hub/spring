package br.ifs.web1.controller;

import br.ifs.web1.dto.PerfilTransacaoDto;
import br.ifs.web1.dto.TokenDto;
import br.ifs.web1.model.PerfilTransacao;
import br.ifs.web1.service.PerfilTransacaoService;
import br.ifs.web1.util.ResponseDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/perfiltransacao")
public class PerfilTransacaoController {

    @Autowired
    PerfilTransacaoService perfilTransacaoService;

    @GetMapping(value = "/getAll")
    public Object listar(@RequestBody TokenDto tokenDto)
    {
        ResponseDefault response = new ResponseDefault();
        try {
            return perfilTransacaoService.findAllPerTran(tokenDto);
        }catch (Exception e ){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return response;
        }
    }

//    @GetMapping(value = "/getAll")
//    public Object listar(@RequestBody TokenDto tokenDto){
//        ResponseDefault response = new ResponseDefault();
//        try {
//            return transacaoService.findAllTransacao(tokenDto);
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//            return response;
//        }
//
//
//    }

    @PostMapping(value = "/createperfiltransacao")
    public Object criarPerfilTransacao(@RequestBody PerfilTransacaoDto perfilTransacaoDto){
        ResponseDefault response = new ResponseDefault();
        try {
            perfilTransacaoService.create(perfilTransacaoDto);
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

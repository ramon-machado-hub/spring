package br.ifs.web1.controller;

import br.ifs.web1.model.LogSystem;
import br.ifs.web1.model.Usuario;
import br.ifs.web1.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping(value = "/getAllLog")
    public List<LogSystem> listar(){
        return logService.listar();
    }

    @GetMapping(value = "/getCreate")
    public List<LogSystem> getByLogTxt(@RequestBody String log){
        return logService.getByLogTxt(log);
    }


}

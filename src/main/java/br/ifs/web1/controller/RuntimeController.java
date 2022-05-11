package br.ifs.web1.controller;

import br.ifs.web1.model.RuntimeDto;
import br.ifs.web1.service.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController("runtime")
public class RuntimeController {
    @Autowired
    RuntimeService runtimeService;

    @PostMapping(value = "/autorized")
    public boolean autorizacao(@RequestBody RuntimeDto runtime){
            return runtimeService.validar(runtime.getToken(), runtime.getUrl());
    }

}

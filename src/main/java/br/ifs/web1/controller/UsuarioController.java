package br.ifs.web1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	
	@GetMapping(value = "/teste")
    public Object getTeste() {
        return "Olá Ramon";
    }
	
	@GetMapping	
	public String hello() {
		return "Olá Ramon";
	}

}

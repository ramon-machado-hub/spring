package br.ifs.web1.controller;

import java.util.List;

import br.ifs.web1.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifs.web1.model.Usuario;
import br.ifs.web1.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	 @Autowired
	 UsuarioService usuarioService;


	@GetMapping(value = "/getTodos")
	public List<Usuario> listar(){
		return usuarioService.listar();		
	}

	@GetMapping(value = "/getAtivos")
	public List<Usuario> getByStatus(@RequestBody String status){
		return usuarioService.getByAtivos(status);
	}

//	@GetMapping(value = "/getAtivos")
//	public Object getAtivos(@RequestBody UsuarioDto usuario){
//		return usuarioService.getAtivos(usuario.getStatus_usuario());
//	}

	@GetMapping(value = "/byEmail")
	public Object getByEmail(@RequestBody String email) {
		return usuarioService.getByEmail(email);
	}

	@GetMapping(value = "/byId")
	public Object getByEmail(@RequestBody int id) {
//		return usuarioService.getById(usuario.getIdUsuario());
		return usuarioService.getById(id);
	}

	@GetMapping(value = "/autenticacao")
	public Object getByLogin(@RequestBody UsuarioDto usuario) {
		return usuarioService.autenticacao(usuario.getEmail_usuario(),
				usuario.getSenha_usuario());
	}


}

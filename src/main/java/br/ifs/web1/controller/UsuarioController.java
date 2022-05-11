package br.ifs.web1.controller;

import java.util.List;

import br.ifs.web1.dto.UsuarioDto;
import br.ifs.web1.util.ResponseDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping(value = "/byEmail")
	public Object getByEmail(@RequestBody String email) {
		return usuarioService.getByEmail(email);
	}

	@GetMapping(value = "/byId")
	public Object getById(@RequestBody int id) {
		return usuarioService.getById(id);
	}

	@GetMapping(value = "/getbyloginsenha")
	public Object getByLoginSenha(@RequestBody UsuarioDto usuario) {
		return usuarioService.getByLoginSenha(usuario.getLogin_usuario(),
				usuario.getSenha_usuario());
	}

//	@GetMapping(value = "/autenticacao")
//	public boolean getByLogin(@RequestBody UsuarioDto usuario) throws Exception {
//		return usuarioService.autenticacao(usuario.getEmail_usuario(),
//				usuario.getSenha_usuario());
//	}

	@PostMapping(value = "/createusuario")
	public Object criarUsuario(@RequestBody UsuarioDto usuario) {
		ResponseDefault response = new ResponseDefault();
		try {
			usuarioService. create(usuario);
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

	@PutMapping(value = "/updateUsuario")
	public Object alterarUsuario(@RequestBody Usuario usuario) {
		ResponseDefault response = new ResponseDefault();
		try {
			usuarioService.update(usuario);
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

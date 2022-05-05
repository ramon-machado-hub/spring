package br.ifs.web1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifs.web1.model.Usuario;
import br.ifs.web1.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;


	public Usuario getByEmail (String email){
		return usuarioRepository.findByEmailUsuario(email);
	}

	public Usuario getById (int id){
		return usuarioRepository.findById(id);
	}

	public List<Usuario> getByAtivos(String status){
		return usuarioRepository.findByStatusUsuario(status);
	}

	public Usuario autenticacao(String login, String senha) {
		return usuarioRepository.findByloginUsuarioAndSenhaUsuario(login, senha);
	}

	public List<Usuario> listar(){
		return (List<Usuario>) usuarioRepository.findAll();
	}





}

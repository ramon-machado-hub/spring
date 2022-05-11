package br.ifs.web1.service;

import java.util.List;
import java.util.Optional;

import br.ifs.web1.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifs.web1.model.Usuario;
import br.ifs.web1.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsuarioService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;

	public List<Usuario> listar(){
		return (List<Usuario>) usuarioRepository.findAll();
	}

	public Usuario getByEmail (String email){

		return usuarioRepository.findByEmailUsuario(email);
	}

	public Usuario getById (int id){
		return usuarioRepository.findById(id);
	}

	public List<Usuario> getByAtivos(String status){
		return usuarioRepository.findByStatusUsuario(status);
	}

	//método que irá validar email e senha
//	public boolean autenticacao(String email, String senha) throws Exception{
//		Usuario usuario = usuarioRepository.findByEmailUsuario(email);
//		if (usuario==null){
//			throw new Exception("Email não pode ser nulo");
//		} else if (usuario.getSenhaUsuario()!=senha){
//			//gerou o token
//			throw new Exception("Senha incorreta");
//		}
//		return true;
//	}



	public Usuario getByLoginSenha(String login, String senha) {

		return usuarioRepository.findByLoginUsuarioAndSenhaUsuario(login, senha);
	}

	public void create(UsuarioDto usuario) throws Exception {
		if (usuario == null) {
			throw new Exception("Usuário é obrigatório");
			} else if (usuario.getNome_usuario() == null || usuario.getNome_usuario().trim().isEmpty()) {
					System.out.println("nome usuario"+usuario.getNome_usuario());
					throw new Exception("Nome do Usuário é obrigatório");
				} else if (usuario.getEmail_usuario().trim().isEmpty()) {
						throw new Exception("Email do Usuário é obrigatório");
					} else if(usuario.getSenha_usuario() == null  || usuario.getSenha_usuario().length()<6){
							throw new Exception("Senha deve conter pelo menos 6 caracteres");
					} else if (usuario.getToken_usuario().trim().isEmpty()){
							throw new Exception("Token não deve ser preenchido no cadastro");
						}
				//		else if (usuarioRepository.findByEmailUsuario(usuario.getLogin_usuario()) == true )
		usuarioRepository.save(usuario.toUsuario());
	}




	public void update(Usuario usuario) {
		Optional<Usuario> opUsu = usuarioRepository.findById(usuario.getIdUsuario());
		if (opUsu.isPresent()) {
			Usuario usuBD = opUsu.get();
			usuBD.setEmailUsuario(usuario.getEmailUsuario());
			usuBD.setNomeUsuario(usuario.getNomeUsuario());
			usuBD.setSenhaUsuario(usuario.getSenhaUsuario());
			usuBD.setStatusUsuario(usuario.getStatusUsuario());
			usuarioRepository.save(usuBD);
		}
	}
}

package br.ifs.web1.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.ifs.web1.dto.RuntimeDto;
import br.ifs.web1.dto.UsuarioDto;
import br.ifs.web1.model.LogSystem;
import br.ifs.web1.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifs.web1.model.Usuario;
import br.ifs.web1.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsuarioService extends BaseService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RuntimeService runtimeService;

	public List<Usuario> listar(){
		return (List<Usuario>) usuarioRepository.findAll();
	}

	public Usuario getByEmail (String email){
		return usuarioRepository.findByEmailUsuario(email);
	}

	public Usuario getById (int id){
		Optional<Usuario> opUsu = usuarioRepository.findById(id);
		if (opUsu.isPresent()) {
			return opUsu.get();
		}
		return null;
	}

	public List<Usuario> getByAtivos(String token) throws Exception{
		System.out.println(
				"token = "+token
		);
		//Usuario usu = getUsuarioByToken(token);
		Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(token));

//		if (usu!=null){
		if (opUsu.isPresent()){
			System.out.println("entrou");
			System.out.println(
					"usu = "+opUsu.get().getNomeUsuario()
			);
			RuntimeDto runtimeDto = new RuntimeDto();
			runtimeDto.setToken(token);
			runtimeDto.setUrl("localhost:8080/usuario/getAtivos");
			if (runtimeService.validar(runtimeDto,opUsu.get().getIdUsuario())){
				System.out.println("validou");
				return usuarioRepository.findByStatusUsuario("A");
			} else {
				System.out.println("não validou");
				throw new Exception("Usuario naao encontrado");
			}
		}else {
			throw new Exception("Usuario nao encontrado");
		}
	}

	public Usuario getByLoginSenha(String login, String senha) throws Exception{
		Usuario usu = usuarioRepository.findByLoginUsuarioAndSenhaUsuario(login, senha);
		if (usu!=null) {
			Date data = new Date();
			Date expira = new Date(data.getTime()+30);
			usu.setTokenUsuario(data.toString());
			usuarioRepository.save(usu);
		} else {
			throw new Exception("Usuário não encontrado");
		}
		return usu;
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
		} else if (!usuario.getToken_usuario().trim().isEmpty()){
			throw new Exception("Token não deve ser preenchido no cadastro");
		}

		Usuario usu = usuario.toUsuario();
		usuarioRepository.save(usu);
		criarLog(usu.getIdUsuario(),"create_usuario");
	}

//	public void autenticate (Usuario usuario){
//		Optional<Usuario> opUsu = usuarioRepository.findById(usuario.getIdUsuario());
//		Date hoje = new Date();
//		Date dataExpiracao = new Date(hoje.getTime() + 300000);
//		if(opUsu.isPresent()){
//			Usuario usuBD = opUsu.get();
//			usuBD.setTokenUsuario(dataExpiracao.toString());
//			criarLog(usuBD.getIdUsuario(),"autenticate");
//			usuarioRepository.save(usuBD);
//		}
//	}


	public void update(Usuario usuario) {
		Optional<Usuario> opUsu = usuarioRepository.findById(usuario.getIdUsuario());
		if (opUsu.isPresent()) {
			Usuario usuBD = opUsu.get();
			usuBD.setEmailUsuario(usuario.getEmailUsuario());
			usuBD.setNomeUsuario(usuario.getNomeUsuario());
			usuBD.setSenhaUsuario(usuario.getSenhaUsuario());
			usuBD.setStatusUsuario(usuario.getStatusUsuario());
			criarLog(usuario.getIdUsuario(),"update_usuario");
			usuarioRepository.save(usuBD);
		}
	}
}

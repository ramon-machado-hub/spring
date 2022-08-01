package br.ifs.web1.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.ifs.web1.dto.RuntimeDto;
import br.ifs.web1.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.ifs.web1.model.Usuario;
import br.ifs.web1.repository.UsuarioRepository;


@Service
public class UsuarioService extends BaseService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private PasswordEncoder encoder;

	public List<Usuario> listar(){
		return (List<Usuario>) usuarioRepository.findAll();
	}


	public Usuario getUserByToken(String token) throws Exception {
		System.out.println("chamou getUser");
		Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(token));
		if (opUsu.isPresent()){
			RuntimeDto runtimeDto = new RuntimeDto();
			runtimeDto.setToken(token);
			runtimeDto.setUrl("localhost:8080/usuario/getUserByToken");
			if (runtimeService.validar(runtimeDto,opUsu.get().getIdUsuario())) {
				System.out.println("validou getUserByToken");
				return usuarioRepository.findByTokenUsuario(runtimeDto.getToken());
			}else {
				System.out.println("não validou");
				throw new Exception("Usuario naao encontrado");
			}

		} else {
			throw new Exception("token não encontrado");
		}
	}

	public Usuario postGetUser(String token) throws Exception {
		Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(token));
		if (opUsu.isPresent()){
			System.out.println("validou postGetUser");
			return usuarioRepository.findByTokenUsuario(token);
		} else {
			System.out.println("não validou");
			throw new Exception("Usuario naao encontrado");
		}
	}

	public List<Usuario> getByAtivos(String token) throws Exception{
		System.out.println("token = "+token);
		Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(token));

		if (opUsu.isPresent()){
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
		} else {
			throw new Exception("token não encontrado");
		}
//			System.out.println("entrou");
//			System.out.println("usu = "+opUsu.get().getNomeUsuario()			);
//			RuntimeDto runtimeDto = new RuntimeDto();
//			runtimeDto.setToken(token);
//			runtimeDto.setUrl("localhost:8080/usuario/getAtivos");
//			if (runtimeService.validar(runtimeDto,opUsu.get().getIdUsuario())){
//				System.out.println("validou");
//				return usuarioRepository.findByStatusUsuario("A");
//			} else {
//				System.out.println("não validou");
//				throw new Exception("Usuario naao encontrado");
//			}
//		}else {
//			throw new Exception("Usuario nao encontrado");
//		}
	}

	public ResponseEntity<Boolean> ValidarSenha(String login, String senha){

		System.out.println(login);
		Optional<Usuario> opUsu = usuarioRepository.findByLoginUsuario(login);
		if (opUsu.isEmpty()){
			System.out.println("entrou");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
		boolean valid = encoder.matches(senha, opUsu.get().getSenhaUsuario());

		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status).body(valid);

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

	public void saveTokenUsuario(String login, String senha, String token){
		Usuario usu = usuarioRepository.findByLoginUsuarioAndSenhaUsuario(login, senha);
		usu.setTokenUsuario(token);
		usuarioRepository.save(usu);
	}

	//alterar para Usuario
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
		System.out.println("aquiii");
		Usuario usu = usuario.toUsuario();
		usu.setSenhaUsuario(encoder.encode(usu.getSenhaUsuario()));
		usuarioRepository.save(usu);
		criarLog(usu.getIdUsuario(),"create_usuario");
	}


	public void update(UsuarioDto usuario) {
		if(!usuario.getToken_usuario().trim().isEmpty()){
			Optional<Usuario> opUsu = Optional.ofNullable(getUsuarioByToken(usuario.getToken_usuario()));
			if (opUsu.isPresent()) {
				RuntimeDto runtimeDto = new RuntimeDto();
				runtimeDto.setToken(usuario.getToken_usuario());
				runtimeDto.setUrl("localhost:8080/usuario/updateUsuario");
				if (runtimeService.validar(runtimeDto,opUsu.get().getIdUsuario())) {
					System.out.println("validou update");
					Usuario usuBD = opUsu.get();
					usuBD.setEmailUsuario(usuario.getEmail_usuario());
					usuBD.setNomeUsuario(usuario.getNome_usuario());
					usuBD.setSenhaUsuario(usuario.getSenha_usuario());
					usuBD.setStatusUsuario(usuario.getStatus_usuario());
					//pega o usuario do token que solicitou a transação
					Usuario usu = usuarioRepository.findByTokenUsuario(runtimeDto.getToken());
					criarLog(usu.getIdUsuario(), "update_usuario");
					usuarioRepository.save(usuBD);
					System.out.println("alterou usuario + criou log");
				}else{
					System.out.println("não validou");
				}
			}
		}

	}
}

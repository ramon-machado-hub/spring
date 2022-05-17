package br.ifs.web1.dto;
import br.ifs.web1.model.Usuario;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class UsuarioDto {
	
	private int id_usuario;
	private String nome_usuario;	
	private String login_usuario;
	private String email_usuario;
	private String senha_usuario;
	private String status_usuario;
	private String token_usuario;


	
	public Usuario toUsuario() {
		
		return Usuario.builder()
				.idUsuario(id_usuario)
				.nomeUsuario(nome_usuario)
				.loginUsuario(login_usuario)
				.emailUsuario(email_usuario)
				.senhaUsuario(senha_usuario)
				.statusUsuario(status_usuario)
				.tokenUsuario(token_usuario)
				.build();
				
	}
}

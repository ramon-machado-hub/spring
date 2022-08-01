package br.ifs.web1.model;
import javax.persistence.*;


import br.ifs.web1.dto.UsuarioDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario", schema = "atv1")
public class Usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;
	
	@Column(name = "nome_usuario")
	private String nomeUsuario;
	
	@Column(unique = true)
    private String loginUsuario;
	
	@Column(name = "email_usuario")
	private String emailUsuario;
	
	@Column(name = "senha_usuario")
    private String senhaUsuario;
	
	@Column(name = "status")
    private String statusUsuario;
	
	@Column(name = "token_usuario")
    private String tokenUsuario;
	
	
	 public UsuarioDto toUsuario() {
		   return UsuarioDto.builder()
	                .id_usuario(idUsuario)
	                .nome_usuario(nomeUsuario)
	                .login_usuario(loginUsuario)
	                .email_usuario(emailUsuario)
	                .senha_usuario(senhaUsuario)
	                .status_usuario(statusUsuario)
	                .token_usuario(tokenUsuario)
	                .build();
	}
}

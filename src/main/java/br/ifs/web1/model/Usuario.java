package br.ifs.web1.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
	
	@Column(name = "login_usuario")
    private String loginUsuario;
	
	@Column(name = "email_usuario")
	private String emailUsuario;
	
	@Column(name = "senha_usuario")
    private String senhaUsuario;
	
	@Column(name = "status_usuario")
    private String statusUsuario;
	
	@Column(name = "token_usuario")
    private String tokenUsuario;
	
	
}

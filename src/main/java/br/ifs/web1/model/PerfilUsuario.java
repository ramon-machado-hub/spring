package br.ifs.web1.model;

import br.ifs.web1.dto.PerfilUsuarioDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perfil_usuario", schema = "atv1")
public class PerfilUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_per_usu")
    private int idPerUsu;

    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "id_perfil")
    private int idPerfil;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_fim")
    private Date dataFim;

    public PerfilUsuarioDto toPerfilUsuario(){
        return PerfilUsuarioDto.builder()
                .id_perUsu(idPerUsu)
                .id_usuario(idUsuario)
                .id_perfil(idPerfil)
                .data_inicio(dataInicio)
                .data_fim(dataFim).build();
    }

}

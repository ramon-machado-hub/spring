package br.ifs.web1.model;

import br.ifs.web1.dto.PerfilDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perfil", schema = "atv1")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Integer idPerfil;


    @Column(name = "nome_perfil")
    private String nomePerfil;

    @Column(name = "status_perfil")
    private String statusPerfil;

    public PerfilDto toPerfil(){
        return PerfilDto.builder()
                .id_perfil(idPerfil)
                .nome_perfil(nomePerfil)
                .status_perfil(statusPerfil)
                .build();
    }
}

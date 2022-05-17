package br.ifs.web1.dto;

import br.ifs.web1.model.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PerfilDto extends TokenDto{
    private int id_perfil;
    private String nome_perfil;
    private String status_perfil;


    public Perfil toPerfil(){
        return Perfil.builder()
                .idPerfil(id_perfil)
                .nomePerfil(nome_perfil)
                .statusPerfil(status_perfil)
                .build();
    }
}

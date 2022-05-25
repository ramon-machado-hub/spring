package br.ifs.web1.dto;

import br.ifs.web1.model.PerfilTransacao;
import br.ifs.web1.model.PerfilUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PerfilUsuarioDto extends TokenDto{


    private int id_perUsu;
    private int id_usuario;
    private int id_perfil;
    private Date data_inicio;
    private Date data_fim;

    public PerfilUsuario toPerfilUsuario(){
        return PerfilUsuario.builder()
                .idPerUsu(id_perUsu)
                .idUsuario(id_usuario)
                .idPerfil(id_perfil)
                .dataInicio(data_inicio)
                .dataFim(data_fim).build();
    }

}

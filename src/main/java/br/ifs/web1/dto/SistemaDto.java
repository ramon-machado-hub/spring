package br.ifs.web1.dto;

import br.ifs.web1.model.Sistema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SistemaDto extends TokenDto {
    private int id_sistema;
    private String nome_sistema;
    private String status_sistema;

    public Sistema toSistema(){
        return  Sistema.builder()
                .idSistema(id_sistema)
                .nomeSistema(nome_sistema)
                .statusSistema(status_sistema).build();
    }
}

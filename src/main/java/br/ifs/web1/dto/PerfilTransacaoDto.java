package br.ifs.web1.dto;

import br.ifs.web1.model.Perfil;
import br.ifs.web1.model.PerfilTransacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PerfilTransacaoDto extends TokenDto{
    private int idPerTran;
    private int idTransacao;
    private int idPerfil;

    public PerfilTransacao toPerfilTransacao(){
        return PerfilTransacao.builder()
                .idPerTran(idPerTran)
                .idTransacao(idTransacao)
                .idPerfil(idPerfil)
                .build();

    }
}

package br.ifs.web1.dto;

import br.ifs.web1.model.Transacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TransacaoDto extends TokenDto{
    private int id_transacao;
    private String nome_transacao;
    private String status_transacao;
    private String url_transacao;
    private int id_servico;

    public Transacao toTransacao(){
        return Transacao.builder()
                .idTransacao(id_transacao)
                .nomeTransacao(nome_transacao)
                .statusTransacao(status_transacao)
                .urlTransacao(url_transacao)
                .idServico(id_servico).build();
    }
}

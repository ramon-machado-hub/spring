package br.ifs.web1.dto;

import br.ifs.web1.model.Servico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class ServicoDto extends TokenDto{
    private int id_servico;
    private String nome_servico;
    private String status_servico;
    private String url_servico;
    private int id_sistema;

    public Servico toServico() {
        return Servico.builder()
                .idServico(id_servico)
                .nomeServico(nome_servico)
                .statusServico(status_servico)
                .urlServico(url_servico)
                .idSistema(id_sistema).build();
    }
}

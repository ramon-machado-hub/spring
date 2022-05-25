package br.ifs.web1.model;

import br.ifs.web1.dto.ServicoDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "servico", schema = "atv1")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico")
    private int idServico;

    @Column(name = "nome_servico")
    private String nomeServico;

    @Column(name = "status_servico")
    private String statusServico;

    @Column(name = "url_servico")
    private String urlServico;

    @Column(name = "id_sistema")
    private int idSistema;

    public ServicoDto toServico(){
        return ServicoDto.builder()
                .id_servico(idServico)
                .nome_servico(nomeServico)
                .status_servico(statusServico)
                .url_servico(urlServico)
                .id_sistema(idSistema).build();
    }
}

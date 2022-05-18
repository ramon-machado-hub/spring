package br.ifs.web1.model;

import br.ifs.web1.dto.TransacaoDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transacao", schema = "atv1")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private int idTransacao;

    @Column(name = "nome_transacao")
    private String nomeTransacao;

    @Column(name = "status_transacao")
    private String statusTransacao;

    @Column(name = "url_transacao")
    private String urlTransacao;

    @Column(name = "id_servico")
    private int idServico;

    public TransacaoDto toTransacao() {
        return TransacaoDto.builder()
                .id_transacao(idTransacao)
                .nome_transacao(nomeTransacao)
                .status_transacao(statusTransacao)
                .url_transacao(urlTransacao)
                .id_servico(idServico).build();
    }
}

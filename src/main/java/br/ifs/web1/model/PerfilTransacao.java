package br.ifs.web1.model;

import br.ifs.web1.dto.PerfilTransacaoDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perfil_transacao", schema = "atv1")
public class PerfilTransacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil_transacao")
    private int idPerTran;

    @Column(name = "id_transacao")
    private int idTransacao;


    @Column(name = "id_perfil")
    private int idPerfil;

    public PerfilTransacaoDto toPerfilTransacao() {
        return PerfilTransacaoDto.builder()
                .idPerTran(idPerTran)
                .idTransacao(idTransacao)
                .idPerfil(idPerfil).build();
    }
}

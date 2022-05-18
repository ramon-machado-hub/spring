package br.ifs.web1.model;

import br.ifs.web1.dto.SistemaDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sistema", schema = "atv1")
public class Sistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sistema")
    private int idSistema;

    @Column(name = "nome_sistema")
    private String nomeSistema;

    @Column(name = "status_sistema")
    private String statusSistema;

    public SistemaDto toSistema(){
        return SistemaDto.builder()
                .id_sistema(idSistema)
                .nome_sistema(nomeSistema)
                .status_sistema(statusSistema).build();
    }

}

package br.ifs.web1.model;

import br.ifs.web1.dto.LogDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "log_sistema", schema = "atv1")
public class LogSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Integer idLog;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "log_txt")
    private String logTxt;

    @Column(name = "date_log")
    private String dateLog;

    public LogDto toLogSystem() {
        return LogDto.builder()
                .id_log(idLog)
                .id_usuario(idUsuario)
                .log_txt(logTxt)
                .date_log(dateLog)
                .build();
    }
}

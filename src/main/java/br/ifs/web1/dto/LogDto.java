package br.ifs.web1.dto;

import br.ifs.web1.model.LogSystem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LogDto {
    private int id_log;
    private int id_usuario;
    private String log_txt;
    private String date_log;

    public LogSystem toLog() {
        return LogSystem.builder()
                .idLog(id_log)
                .idUsuario(id_usuario)
                .logTxt(log_txt)
                .dateLog(date_log)
                .build();
    }
}

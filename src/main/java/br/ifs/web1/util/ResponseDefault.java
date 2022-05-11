package br.ifs.web1.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDefault {
    private String mensagem;
    private int codigo;
    private Object value;
}

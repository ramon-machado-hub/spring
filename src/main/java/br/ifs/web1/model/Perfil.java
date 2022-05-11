package br.ifs.web1.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perfil", schema = "atv1")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Integer idUsuario;


    @Column(name = "nomePerfil")
    private String nomePerfil;

    @Column(name = "status_perfil")
    private String statusPerfil;

}

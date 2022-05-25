package br.ifs.web1.repository;

import br.ifs.web1.model.Transacao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Integer> {

    int getIdByUrlTransacao(String url);

    @Query(nativeQuery = true, value =
            "select  t.*  from atv1.transacao t " +
                    "inner join atv1.perfil_transacao pt " +
                    "on pt.id_transacao = t.id_transacao " +
                    "inner join atv1.perfil_usuario pu " +
                    "on pu.id_perfil = pt.id_perfil " +
                    "where t.url_transacao = ?2 "+
                    "and pu.id_usuario = ?1")
    List<Transacao> getByIdUsuarioAndUrl(int id_usuario, String url);

}

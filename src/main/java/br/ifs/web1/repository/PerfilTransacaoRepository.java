package br.ifs.web1.repository;

import br.ifs.web1.model.PerfilTransacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilTransacaoRepository extends CrudRepository<PerfilTransacao, Integer> {


}

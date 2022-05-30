package br.ifs.web1.repository;

import br.ifs.web1.model.Sistema;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SistemaRepository extends CrudRepository<Sistema, Integer> {

}

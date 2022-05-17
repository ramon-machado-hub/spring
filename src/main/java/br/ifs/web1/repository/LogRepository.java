package br.ifs.web1.repository;

import br.ifs.web1.model.LogSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface LogRepository extends CrudRepository<LogSystem, Integer> {
    List<LogSystem> findByLogTxt(String exception);

}

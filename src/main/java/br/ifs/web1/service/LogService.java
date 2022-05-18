package br.ifs.web1.service;

import br.ifs.web1.dto.LogDto;
import br.ifs.web1.model.LogSystem;
import br.ifs.web1.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    //testar
    public List<LogSystem> listar(){
        return (List<LogSystem>) logRepository.findAll();
    }

    //testar
    public List<LogSystem> getByLogTxt(String exception) {
        return logRepository.getByLogTxt(exception);
    }

    public void create(LogDto log) throws Exception {
        if(log == null){
            throw new Exception("Log é obrigatório");
        }
        logRepository.save(log.toLog());
    }
}

package br.ifs.web1.service;

import br.ifs.web1.model.Perfil;
import br.ifs.web1.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public List<Perfil> listar(){
        return (List<Perfil>) perfilRepository.findAll();
    }



}

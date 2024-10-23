package com.seguranca_info.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.seguranca_info.demo.dto.TermosDeUsoDto;
import com.seguranca_info.demo.models.TermosDeUso;
import com.seguranca_info.demo.models.Usuario;
import com.seguranca_info.demo.repository.TermosDeUsoRepository;
import com.seguranca_info.demo.repository.UsuarioRepository;

@Service
public class TermosDeUsoService {
    @Autowired
    private TermosDeUsoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public TermosDeUso createTermosDeUso(TermosDeUsoDto termosDeUso) throws NotFoundException {

        Optional<Usuario> response = usuarioRepository.findByUsername(termosDeUso.username());

        if (!response.isPresent()){
            throw new NotFoundException();
        }

        Usuario user = response.get();

        TermosDeUso termos = new TermosDeUso();

        termos.setIdUser(user.getId());
        termos.setTermosDeUso(termosDeUso.termosDeUso());

        return repository.save(termos);
    }
    
    public TermosDeUso getTermosDeUso(String username) throws NotFoundException {
        Optional<Usuario> response = usuarioRepository.findByUsername(username);

        if (!response.isPresent()){
            throw new NotFoundException();
        }

        Usuario user = response.get();

        return repository.findByIdUser(user.getId()).orElseThrow();
    }

    public TermosDeUso updateTermosDeUso(String id,TermosDeUsoDto termosDeUso) throws NotFoundException{
        Optional <TermosDeUso> response = this.repository.findById(id);

        if (!response.isPresent()){
            throw new NotFoundException();
        }

        TermosDeUso termos2 = response.get();

        termos2.setTermosDeUso(termosDeUso.termosDeUso());

        return repository.save(termos2);
    }

    public void deleteTermosDeUso(String id) throws NotFoundException {
        Optional <TermosDeUso> response = this.repository.findById(id);

        if (!response.isPresent()){
            throw new NotFoundException();
        }

        TermosDeUso termos3 = response.get();

        this.repository.delete(termos3);

    }
}

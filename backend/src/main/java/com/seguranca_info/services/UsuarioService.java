package com.seguranca_info.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguranca_info.models.Usuario;
import com.seguranca_info.repository.UsuarioRepository;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario create(Usuario usuario){
        return this.usuarioRepository.save(usuario);
    }

    public List<Usuario> getAll(){
        return this.usuarioRepository.findAll().stream().sorted(Comparator.comparing(user -> user.getCreatedAt())).collect(Collectors.toList());
    }

    public Usuario getById(String id) throws Exception{
        Optional<Usuario> resp = this.usuarioRepository.findById(id);

        if (!resp.isPresent()) {
            throw new Exception("Usuário não existe!");
        } else {
            return resp.get();
        }
    }

    public Usuario update(String usuarioId , Usuario usuario) throws Exception{
        Usuario usuarioAtualizado = this.getById(usuarioId);

        usuarioAtualizado.setEmail(usuario.getEmail());
        usuarioAtualizado.setSenha(usuario.getSenha());
        usuarioAtualizado.setUsername(usuario.getUsername());

        return this.usuarioRepository.save(usuarioAtualizado);
    }
}

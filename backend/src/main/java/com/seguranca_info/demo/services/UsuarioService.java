package com.seguranca_info.demo.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.seguranca_info.demo.dto.UserDto;

import com.seguranca_info.demo.models.Usuario;
import com.seguranca_info.demo.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEnconder;

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

    public Usuario update(String usuarioId , UserDto usuario) throws Exception{
        Usuario usuarioAtualizado = this.getById(usuarioId);

        usuarioAtualizado.setEmail(usuario.email());
        usuarioAtualizado.setSenha( passwordEnconder.encode(usuario.senha()));
        usuarioAtualizado.setUsername(usuario.username());

        return this.usuarioRepository.save(usuarioAtualizado);
    }
}

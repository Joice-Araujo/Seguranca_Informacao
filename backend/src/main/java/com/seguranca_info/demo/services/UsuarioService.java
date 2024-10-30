package com.seguranca_info.demo.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seguranca_info.demo.dto.ChangePasswordDto;
import com.seguranca_info.demo.dto.UserDto;

import com.seguranca_info.demo.models.Usuario;
import com.seguranca_info.demo.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> getAll() {
        return this.usuarioRepository.findAll().stream().sorted(Comparator.comparing(user -> user.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public Usuario getById(String id) throws Exception {
        Optional<Usuario> resp = this.usuarioRepository.findById(id);

        if (!resp.isPresent()) {
            throw new Exception("Usuário não existe!");
        } else {
            return resp.get();
        }
    }

    public Usuario getUsuarioByUsername(String username) {
        return this.usuarioRepository.findByUsername(username).orElseThrow();
    }

    public ResponseEntity<Usuario> update(String usuarioId, UserDto usuario) throws Exception {
        Optional<Usuario> userExist = this.usuarioRepository.findByUsername(usuario.username());

        if (userExist.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Usuario usuarioAtualizado = this.getById(usuarioId);
        usuarioAtualizado.setEmail(usuario.email());
        usuarioAtualizado.setUsername(usuario.username());

        Usuario response = this.usuarioRepository.save(usuarioAtualizado);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> updateSenha(String usuarioId, ChangePasswordDto data) {
        Optional<Usuario> response = this.usuarioRepository.findById(usuarioId);

        if (!response.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Usuario usuario = response.get();

        if (!passwordEncoder.matches(data.senhaAtual(), usuario.getPassword())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        usuario.setSenha(passwordEncoder.encode(data.novaSenha()));

        usuarioRepository.save(usuario);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
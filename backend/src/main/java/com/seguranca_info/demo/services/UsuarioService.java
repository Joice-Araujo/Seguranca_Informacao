package com.seguranca_info.demo.services;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.Base64;
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
import com.seguranca_info.demo.dto.UserWithToken;
import com.seguranca_info.demo.helpers.Criptografia;
import com.seguranca_info.demo.models.UserSecurity;
import com.seguranca_info.demo.models.Usuario;
import com.seguranca_info.demo.repository.UsuarioRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private Criptografia criptografia;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public List<Usuario> getAll() {
        return this.usuarioRepository.findAll().stream().sorted(Comparator.comparing(user -> user.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public Usuario getById(String id) throws Exception {
        Optional<Usuario> resp = this.usuarioRepository.findById(id);

        if (!resp.isPresent()) {
            throw new Exception("Usuário não existe!");
        } else {
            Usuario user = resp.get();

            UserSecurity userSecurity = userSecurityService.getUserSecurity(user.getId());

            PrivateKey privateKey = Criptografia.Base64ToPrivateKey(userSecurity.getPrivateKey());

            byte[] bytesEmail = Base64.getDecoder().decode(user.getEmail());

            String email = criptografia.descriptografar(bytesEmail, privateKey,
                    criptografia.getAlgoritmo());

            user.setEmail(email);

            return user;
        }
    }

    public Usuario getUsuarioByUsername(String username) throws Exception {

        try {
            Optional<Usuario> response = this.usuarioRepository.findByUsername(username);

            if (!response.isPresent()) {
                System.out.println("Usuario não encontrado");
                return null;
            }

            Usuario user = response.get();

            UserSecurity userSecurity = userSecurityService.getUserSecurity(user.getId());

            PrivateKey privateKey = Criptografia.Base64ToPrivateKey(userSecurity.getPrivateKey());

            byte[] bytesEmail = Base64.getDecoder().decode(user.getEmail());

            String email = criptografia.descriptografar(bytesEmail, privateKey,
                    criptografia.getAlgoritmo());

            user.setEmail(email);

            return user;

        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<UserWithToken> update(String usuarioId, UserDto usuario) throws Exception {
        Optional<Usuario> userExist = this.usuarioRepository.findByUsername(usuario.username());

        if (userExist.isPresent() && userExist.get().getId() != usuarioId) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Usuario usuarioAtualizado = this.getById(usuarioId);

        KeyPair chaves = criptografia.gerarChave();

        byte[] emailCriptografado = criptografia.criptografar(usuarioAtualizado.getEmail(), chaves.getPublic());

        usuarioAtualizado.setEmail(Base64.getEncoder().encodeToString(emailCriptografado));

        usuarioAtualizado.setUsername(usuario.username());

        Usuario response = this.usuarioRepository.save(usuarioAtualizado);

        userSecurityService.updatePrivateKey(usuarioId, chaves.getPrivate(), criptografia.getAlgoritmo(),
                criptografia.getKeySize());

        String token = this.jwtService.generateToken(response);

        return new ResponseEntity<>(new UserWithToken(response, token), HttpStatus.OK);
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

    public ResponseEntity<HttpStatus> deleteUser(String id) throws Exception {
        try {
            Usuario user = this.getById(id);

            this.usuarioRepository.delete(user);
            this.userSecurityService.deletePrivateKey(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
    }
}
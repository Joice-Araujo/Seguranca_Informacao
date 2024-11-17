package com.seguranca_info.demo.services;

import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.seguranca_info.demo.dto.LoginDto;
import com.seguranca_info.demo.dto.UserSecurityDto;
import com.seguranca_info.demo.helpers.Criptografia;
import com.seguranca_info.demo.dto.UserDto;
import com.seguranca_info.demo.repository.UsuarioRepository;
import com.seguranca_info.demo.models.AuthenticationResponse;
import com.seguranca_info.demo.models.Usuario;

@Service
public class AuthenticationService {
    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEnconder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private Criptografia criptografiaSevice;

    public AuthenticationResponse register(UserDto dto) {
        Usuario user = new Usuario(dto);

        KeyPair chaves = criptografiaSevice.gerarChave();

        byte[] dadoCriptografado = criptografiaSevice.criptografar(user.getEmail(), chaves.getPublic());

        user.setSenha(passwordEnconder.encode(dto.senha()));

        user.setEmail(Base64.getEncoder().encodeToString(dadoCriptografado));

        Usuario userSaved = userRepository.save(user);

        UserSecurityDto userSecurityDto = new UserSecurityDto();

        userSecurityDto.setAlgotimo(criptografiaSevice.getAlgoritmo());
        userSecurityDto.setIdUser(userSaved.getId());
        userSecurityDto.setKeySize(criptografiaSevice.getKeySize());
        userSecurityDto.setPrivateKey(chaves.getPrivate());

        userSecurityService.createUserSecurity(userSecurityDto);

        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(LoginDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.senha()));

        Optional<Usuario> user = userRepository.findByUsername(dto.username());

        if (!user.isPresent()) {
            System.out.println("Usuario nao encontrado");
            return null;
        } else {
            String token = jwtService.generateToken(user.get());

            return new AuthenticationResponse(token);
        }
    }

}

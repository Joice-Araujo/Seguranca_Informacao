package com.seguranca_info.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.seguranca_info.demo.dto.LoginDto;
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

    public AuthenticationResponse register(UserDto dto ){
        Usuario user = new Usuario(dto);
        user.setSenha(passwordEnconder.encode(dto.senha()));

        userRepository.save(user);
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(LoginDto dto){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.username(), dto.senha())
        );

        Usuario user = userRepository.findByUsername(dto.username()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }


}

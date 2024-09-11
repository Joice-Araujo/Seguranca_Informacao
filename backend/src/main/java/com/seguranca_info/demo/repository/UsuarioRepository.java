package com.seguranca_info.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.seguranca_info.demo.models.Usuario;

import java.util.Optional;


public interface UsuarioRepository extends MongoRepository<Usuario, String>{
    Optional<Usuario> findByUsername(String username);
}

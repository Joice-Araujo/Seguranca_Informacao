package com.seguranca_info.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.seguranca_info.models.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{

}

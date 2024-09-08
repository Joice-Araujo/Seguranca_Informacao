package com.seguranca_info.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.seguranca_info.demo.models.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{

}

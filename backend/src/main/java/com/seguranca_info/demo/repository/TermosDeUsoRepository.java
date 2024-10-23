package com.seguranca_info.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.seguranca_info.demo.models.TermosDeUso;


public interface TermosDeUsoRepository extends MongoRepository <TermosDeUso, String>{
    Optional<TermosDeUso> findByIdUser(String idUser);

    
}

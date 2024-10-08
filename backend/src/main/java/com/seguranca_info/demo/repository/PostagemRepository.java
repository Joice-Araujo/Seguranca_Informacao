package com.seguranca_info.demo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.seguranca_info.demo.models.Postagem;

import java.util.List;


public interface PostagemRepository extends MongoRepository <Postagem, String>{
    List<Postagem> findByUserId(String userId); 
    List<Postagem> findByTitulo(String titulo);
}

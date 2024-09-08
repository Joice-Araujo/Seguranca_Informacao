package com.seguranca_info.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.seguranca_info.models.Postagem;
import com.seguranca_info.repository.PostagemRepository;


public class PostagemService {
    @Autowired
    private PostagemRepository repository;

    public Postagem createPostagem(Postagem postagem) {
        return repository.save(postagem);
    }

    public Postagem getOnePostagem(String id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Postagem> getPostagemByUser(String userId) {
        return repository.findByUserId(userId)
        .stream()
        .sorted(Comparator.comparing(post -> post.getCreatedAt()))
        .collect(Collectors.toList());
    }

    public List<Postagem> getPostagemByTitle(String title) {
        return repository.findByTitulo(title)
        .stream()
        .sorted(Comparator.comparing(post -> post.getCreatedAt()))
        .collect(Collectors.toList());
    }

    public Postagem updatePostagem(String id,Postagem postagem) {
        Postagem updatedPostagem = this.getOnePostagem(id);

        updatedPostagem.setConteudo(postagem.getConteudo());
        updatedPostagem.setTitulo(postagem.getTitulo());

        return repository.save(updatedPostagem);
    }

    public void deletePostagem(String id) {
        Postagem postagem = this.getOnePostagem(id);
        this.repository.delete(postagem);
    }

}

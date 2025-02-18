package com.seguranca_info.demo.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.seguranca_info.demo.dto.PostagemDto;
import com.seguranca_info.demo.models.Postagem;
import com.seguranca_info.demo.models.Usuario;
import com.seguranca_info.demo.repository.PostagemRepository;
import com.seguranca_info.demo.repository.UsuarioRepository;

@Service
public class PostagemService {
    @Autowired
    private PostagemRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Postagem createPostagem(PostagemDto postagem) throws NotFoundException {
        Optional<Usuario> response = usuarioRepository.findByUsername(postagem.username());

        if (!response.isPresent()) {
            throw new NotFoundException();
        }

        Usuario user = response.get();

        Postagem post = new Postagem();

        post.setConteudo(postagem.conteudo());
        post.setTitulo(postagem.titulo());
        post.setUserId(user.getId());
        post.setCreatedAt(new Date());

        return repository.save(post);
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

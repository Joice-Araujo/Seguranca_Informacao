package com.seguranca_info.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguranca_info.demo.models.Postagem;
import com.seguranca_info.demo.services.PostagemService;

@RestController
@RequestMapping("/postagem")
public class PostagemController {
    
    @Autowired
    private PostagemService postagemService;

    @PostMapping
    public ResponseEntity<Postagem> createPostagem(@RequestBody Postagem postagem) {
        Postagem createdPostagem = postagemService.createPostagem(postagem);
        return new ResponseEntity<>(createdPostagem, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> getPostagemById(@PathVariable String id) {
        try {
            Postagem postagem = postagemService.getOnePostagem(id);
            return new ResponseEntity<>(postagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Postagem>> getPostagemByUser(@PathVariable String userId) {
        List<Postagem> postagens = postagemService.getPostagemByUser(userId);
        return new ResponseEntity<>(postagens, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Postagem>> getPostagemByTitle(@PathVariable String title) {
        List<Postagem> postagens = postagemService.getPostagemByTitle(title);
        return new ResponseEntity<>(postagens, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postagem> updatePostagem(@PathVariable String id, @RequestBody Postagem postagem) {
        try { 
            Postagem updatedPostagem = postagemService.updatePostagem(id,postagem);
            return new ResponseEntity<>(updatedPostagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostagem(@PathVariable String id) {
        try {
            postagemService.deletePostagem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

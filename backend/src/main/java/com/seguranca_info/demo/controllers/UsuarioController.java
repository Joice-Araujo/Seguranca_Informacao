package com.seguranca_info.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguranca_info.demo.models.Usuario;
import com.seguranca_info.demo.services.UsuarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.seguranca_info.demo.dto.UserDto;



@RequestMapping("usuario")
@RestController
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findUsuarioById(@PathVariable("id") String id) throws Exception {
        try {
            Usuario usuario = this.service.getById(id);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsuario(){
        List<Usuario> usuarios = this.service.getAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody UserDto usuario, @PathVariable("id") String id) throws Exception{
        try {
            Usuario usuarioUpdated = this.service.update(id, usuario);
            return new ResponseEntity<>(usuarioUpdated, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

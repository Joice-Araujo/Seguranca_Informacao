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

import com.seguranca_info.demo.dto.ChangePasswordDto;
import com.seguranca_info.demo.dto.UserDto;
import com.seguranca_info.demo.dto.UserWithToken;

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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsuario() {
        List<Usuario> usuarios = this.service.getAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Usuario> getUserByUsername(@PathVariable("username") String username) {
        try {
            Usuario user = service.getUsuarioByUsername(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserWithToken> updateUsuario(@RequestBody UserDto usuario, @PathVariable("id") String id)
            throws Exception {
        return this.service.update(id, usuario);
    }

    @PutMapping("change-password/{id}")
    public ResponseEntity<HttpStatus> changePassoword(@PathVariable String id, @RequestBody ChangePasswordDto data) {
        return service.updateSenha(id, data);
    }
}
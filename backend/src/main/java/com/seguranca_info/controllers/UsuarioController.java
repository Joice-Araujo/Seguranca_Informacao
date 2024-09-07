package com.seguranca_info.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguranca_info.models.Usuario;
import com.seguranca_info.services.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("usuario")
@RestController
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;

    @GetMapping(value = "/{id}")
    public Usuario findUsuarioById(@PathVariable("id") String id) throws Exception {
        return this.service.getById(id);
    }

    @GetMapping
    public List<Usuario> findAllUsuario(){
        return this.service.getAll();
    }

    
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return this.service.create(usuario);
    }

    @PutMapping(value = "/{id}")
    public Usuario updateUsuario(@RequestBody Usuario usuario, @PathVariable("id") String id) throws Exception{
        return this.service.update(id, usuario);
    }
}

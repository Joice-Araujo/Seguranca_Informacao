package com.seguranca_info.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguranca_info.demo.dto.AssinantesTermosDeUsoDto;
import com.seguranca_info.demo.dto.TermosDeUsoDto;
import com.seguranca_info.demo.models.TermosDeUso;
import com.seguranca_info.demo.services.TermosDeUsoService;


@RequestMapping("termodeuso")
@RestController
public class TermosDeUsoController {
    @Autowired
    private TermosDeUsoService service;

    @PostMapping("/create-termo")
    public ResponseEntity<TermosDeUso> createTermo(@RequestBody TermosDeUsoDto dto){
        System.out.println(dto.descricao());
        ResponseEntity<TermosDeUso> response = service.create(dto);
        return response;
    }

    @PutMapping("/add-assinante")
    public ResponseEntity<HttpStatus> addAssinante(@RequestBody AssinantesTermosDeUsoDto dto){
        ResponseEntity<HttpStatus> response = service.adicionarAssinante(dto);
        return response;
    }
}

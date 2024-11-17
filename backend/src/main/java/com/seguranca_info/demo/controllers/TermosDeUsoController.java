package com.seguranca_info.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguranca_info.demo.dto.AssinantesTermosDeUso;
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
        ResponseEntity<TermosDeUso> response = service.create(dto);
        return response;
    }

    @PutMapping("/add-assinante")
    public ResponseEntity<HttpStatus> addAssinante(@RequestBody AssinantesTermosDeUsoDto dto){
        ResponseEntity<HttpStatus> response = service.adicionarAssinante(dto);
        return response;
    }

    @GetMapping("/atual")
    public ResponseEntity<TermosDeUsoDto> getTermoAtual(){
        TermosDeUsoDto termo = service.getActualTermoDeUso();
        if (termo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TermosDeUsoDto>(termo, HttpStatus.OK);
    }

    @GetMapping("/assinatura/{idUser}")
    public ResponseEntity<AssinantesTermosDeUso> getAssinaturas(@PathVariable("idUser") String idUser){
        AssinantesTermosDeUso assinatura = service.getTermosAssinados(idUser);
        if(assinatura == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assinatura, HttpStatus.OK);
    }

    @GetMapping("/user/isactive/{idUser}")
    public ResponseEntity<HttpStatus> userIsRegistered(@PathVariable("idUser") String idUser){
        Boolean isRegistered = service.userIsRegistered(idUser);
        if (isRegistered) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

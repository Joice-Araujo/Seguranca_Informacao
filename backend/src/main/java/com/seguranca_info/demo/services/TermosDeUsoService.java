package com.seguranca_info.demo.services;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seguranca_info.demo.dto.AssinantesTermosDeUso;
import com.seguranca_info.demo.dto.AssinantesTermosDeUsoDto;
import com.seguranca_info.demo.dto.TermosDeUsoDto;
import com.seguranca_info.demo.models.TermosDeUso;
import com.seguranca_info.demo.repository.TermosDeUsoRepository;

@Service
public class TermosDeUsoService {
    @Autowired
    private TermosDeUsoRepository repository;

    public ResponseEntity<TermosDeUso> create(TermosDeUsoDto termosDeUsoDto){
        try {            
            Optional<TermosDeUso> response = repository.findByActual(true);
    
            if (response.isPresent()) {
                TermosDeUso antigoAtual = response.get();
                antigoAtual.setActual(false);
                repository.save(antigoAtual);
            }
    
            TermosDeUso termosDeUso = new TermosDeUso();
            ZonedDateTime dataHoraBrasil = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

    
            termosDeUso.setVersao(termosDeUsoDto.versao());
            termosDeUso.setDescricao(termosDeUsoDto.descricao());
            termosDeUso.setOpcoes(termosDeUsoDto.opcoes());
            termosDeUso.setActual(true);
            termosDeUso.setCreatedAt(dataHoraBrasil.toInstant());
    
            TermosDeUso termoSalvo = repository.save(termosDeUso);
    
            return new ResponseEntity<TermosDeUso>(termoSalvo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<HttpStatus> adicionarAssinante(AssinantesTermosDeUsoDto dto){
        Optional<TermosDeUso> response = repository.findByActual(true);
        if (!response.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TermosDeUso termosDeUso = response.get();
        ZonedDateTime dataHoraBrasil = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

        AssinantesTermosDeUso assinante = new AssinantesTermosDeUso();
        assinante.setIdUsuario(dto.idUsuario());
        assinante.setOpcoes(dto.opcoes());
        assinante.setCreatedAt(dataHoraBrasil.toInstant());
        termosDeUso.addAssinante(assinante);
        
        repository.save(termosDeUso);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public TermosDeUso getActualTermoDeUso(){
        Optional<TermosDeUso> response = repository.findByActual(true);
        if (!response.isPresent()) {
            return null;
        }

        TermosDeUso termo = response.get();

        return termo;
    }

    public Boolean userIsRegistered( String userId ){
        TermosDeUso termo = getActualTermoDeUso();

        for (AssinantesTermosDeUso assinantes : termo.getAssinantes()) {
            if (assinantes.getIdUsuario().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public AssinantesTermosDeUso getTermosAssinados(String userId) {
        TermosDeUso termo = getActualTermoDeUso();

        if (termo == null) {
            return null;
        }

        Optional<AssinantesTermosDeUso> assinante = termo.getAssinantes().stream().filter(ass -> ass.getIdUsuario().equals(userId)).reduce((primeiro, segundo) -> segundo);

        if (assinante.isPresent()) {
            return assinante.get();
        }

        return null;
    }
}

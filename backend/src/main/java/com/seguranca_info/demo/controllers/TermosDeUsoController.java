package com.seguranca_info.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguranca_info.demo.dto.TermosDeUsoDto;
import com.seguranca_info.demo.models.TermosDeUso;
import com.seguranca_info.demo.services.TermosDeUsoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("termosDeUso")
public class TermosDeUsoController {

    @Autowired
    private TermosDeUsoService termosDeUsoService;

    @PostMapping
    public ResponseEntity<TermosDeUso> createTermosDeUso(@RequestBody TermosDeUsoDto termosDeUso) throws NotFoundException {
        try {
            TermosDeUso createdTermosDeUso = termosDeUsoService.createTermosDeUso(termosDeUso);
            return new ResponseEntity<>(createdTermosDeUso, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } 

    @GetMapping("/{username}")
    public ResponseEntity<TermosDeUso> getTermosDeUsoById(@PathVariable String username) {
        try {
            TermosDeUso termosDeUso = termosDeUsoService.getTermosDeUso(username);
            return new ResponseEntity<>(termosDeUso, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<TermosDeUso> updateTermosDeUso(@PathVariable String id, @RequestBody TermosDeUsoDto termosDeUso) {
        try {
            TermosDeUso updateTermosDeUso = termosDeUsoService.updateTermosDeUso(id, termosDeUso);
            return new ResponseEntity<>(updateTermosDeUso, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTermosDeUso(@PathVariable String id) {
        try {
            termosDeUsoService.deleteTermosDeUso(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}

package com.seguranca_info.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguranca_info.demo.dto.LoginDto;
import com.seguranca_info.demo.dto.UserDto;
import com.seguranca_info.demo.models.AuthenticationResponse;
import com.seguranca_info.demo.services.AuthenticationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping
public class AutenticationController {
    @Autowired
    private AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDto body) {
        return ResponseEntity.ok(service.authenticate(body));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto body) {
        return ResponseEntity.ok(service.register(body));
    }
}

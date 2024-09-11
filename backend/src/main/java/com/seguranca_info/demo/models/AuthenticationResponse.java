package com.seguranca_info.demo.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {
    private String token;

    public AuthenticationResponse(String token){
        this.token = token;
    }
}

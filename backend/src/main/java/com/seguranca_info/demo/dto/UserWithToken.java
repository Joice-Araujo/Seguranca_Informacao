package com.seguranca_info.demo.dto;

import com.seguranca_info.demo.models.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserWithToken {
    private Usuario user;
    private String token;
}

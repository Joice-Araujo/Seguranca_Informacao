package com.seguranca_info.demo.dto;

import java.security.PrivateKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSecurityDto {
    private String idUser;

    private PrivateKey privateKey;

    private String algotimo;

    private Integer keySize;

}
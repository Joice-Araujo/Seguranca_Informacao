package com.seguranca_info.demo.dto;
import javax.crypto.SecretKey;
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

    private SecretKey privateKey;

    private String algotimo;
}
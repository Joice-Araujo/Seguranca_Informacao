package com.seguranca_info.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpcaoTermosDeUsoDto {
    private String descricao;
    private boolean aceito;
}

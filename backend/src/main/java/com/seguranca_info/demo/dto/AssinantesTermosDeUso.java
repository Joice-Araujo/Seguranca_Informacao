package com.seguranca_info.demo.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class AssinantesTermosDeUso {
    private String idUsuario;
    private List<OpcaoTermosDeUsoDto> opcoes;
    private Instant createdAt;
}

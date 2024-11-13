package com.seguranca_info.demo.dto;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public record AssinantesTermosDeUsoDto (
    String idUsuario,
    List<OpcaoTermosDeUsoDto> opcoes
        )  
{}

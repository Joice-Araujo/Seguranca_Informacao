package com.seguranca_info.demo.dto;

import java.util.List;

public record TermosDeUsoDto(
    String versao,
    String descricao,
    List<OpcaoTermosDeUsoDto> opcoes
) {}

package com.seguranca_info.demo.models;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.seguranca_info.demo.dto.AssinantesTermosDeUsoDto;
import com.seguranca_info.demo.dto.OpcaoTermosDeUsoDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class TermosDeUso {
    @Id
    private String id;

    private boolean actual;

    private String versao;

    private String descricao;

    private List<OpcaoTermosDeUsoDto> opcoes;

    private List<AssinantesTermosDeUsoDto> assinantes = new ArrayList<AssinantesTermosDeUsoDto>();

    private Instant createdAt;

    public void addAssinante(AssinantesTermosDeUsoDto assinantesTermosDeUsoDto){
        this.assinantes.add(assinantesTermosDeUsoDto);
    }

}

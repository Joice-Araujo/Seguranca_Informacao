package com.seguranca_info.models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document
public class Postagem {
    private String id;

    private String conteudo;

    private List<String> tags;

    private String titulo;
}

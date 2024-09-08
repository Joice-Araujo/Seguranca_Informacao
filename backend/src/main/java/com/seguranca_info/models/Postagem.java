package com.seguranca_info.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
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
    @Id
    private String id;

    private String userId;

    private String conteudo;

    private String titulo;

    private Date createdAt = new Date();
}

package com.seguranca_info.demo.models;

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
public class Usuario {
    @Id
    private String id;

    private String username;

    private String email;

    private String senha;

    private Date createdAt = new Date();


}

package com.seguranca_info.demo.models;

import java.security.PrivateKey;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSecurity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String idUser;

    private PrivateKey privateKey;

    private Integer algotimo;

    private Integer keySize; 
}

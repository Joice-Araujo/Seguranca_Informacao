package com.seguranca_info.demo.models;

import java.security.PrivateKey;

import jakarta.persistence.Column;
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
@Table(name = "user") // Nome da tabela no banco
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSecurity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "id_user", nullable = false, unique = true)
    private String idUser;

    @Column(name = "private_key")
    private String privateKey; // Armazenando como Base64

    @Column(name = "algoritmo", length = 3)
    private String algoritmo;

    @Column(name = "key_size")
    private Integer keySize;
}

package com.seguranca_info.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seguranca_info.demo.models.UserSecurity;

public interface UserSecurityRepository extends JpaRepository<String, UserSecurity>{
    Optional<UserSecurity> findByIdUser(String idUser);
}

package com.seguranca_info.demo.models;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.seguranca_info.demo.dto.UserDto;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document

public class Usuario implements UserDetails{
    @Id
    private String id;

    private String username;

    private String email;

    private String senha;

    private Role role = Role.USER;

    private Date createdAt = new Date();

    public Role getRole(){
        return this.role;
    }

    public Usuario(UserDto dto){
        this.username = dto.username();
        this.email = dto.email();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername(){
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

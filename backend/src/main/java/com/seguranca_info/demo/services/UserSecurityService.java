package com.seguranca_info.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguranca_info.demo.models.UserSecurity;
import com.seguranca_info.demo.repository.UserSecurityRepository;

@Service
public class UserSecurityService {
    @Autowired
    private UserSecurityRepository repository;

    public UserSecurity getUserSecurity(String userId) {
        Optional<UserSecurity> response = repository.findByIdUser(userId);

        if (!response.isPresent()) {
            return null;
        }

        return response.get();
    }

    public Boolean deletePrivateKey(String userId) {
        UserSecurity user = getUserSecurity(userId);

        if (user == null) {
            return false;
        }

        user.setPrivateKey(null);
        user.setKeySize(null);
        user.setAlgotimo(null);
        
        return true;
    }
}

package com.seguranca_info.demo.services;

import java.security.PrivateKey;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguranca_info.demo.models.UserSecurity;
import com.seguranca_info.demo.dto.UserSecurityDto;
import com.seguranca_info.demo.helpers.Criptografia;
import com.seguranca_info.demo.repository.UserSecurityRepository;

@Service
public class UserSecurityService {
    @Autowired
    private UserSecurityRepository repository;

    @Autowired
    private Criptografia criptografia;

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
        repository.delete(user);
        
        return true;
    }

    public UserSecurity createUserSecurity(UserSecurityDto data ){
        UserSecurity createdUser = new UserSecurity();
        createdUser.setAlgoritmo(criptografia.getAlgoritmo());
        createdUser.setIdUser(data.getIdUser());
        byte[] privateKeyBytes =  data.getPrivateKey().getEncoded(); 
        createdUser.setPrivateKey(privateKeyBytes);
        return repository.save(createdUser);
    }

    public UserSecurity updatePrivateKey(String userId, SecretKey privateKey, String algoritmo){
        UserSecurity updatedUserSecurity = getUserSecurity(userId);
        byte[] privateKeyBytes =  privateKey.getEncoded(); 
        updatedUserSecurity.setPrivateKey(privateKeyBytes);
        updatedUserSecurity.setAlgoritmo(algoritmo);
        return repository.save(updatedUserSecurity);
    }
}

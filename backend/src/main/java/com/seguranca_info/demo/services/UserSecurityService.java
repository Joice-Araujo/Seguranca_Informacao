package com.seguranca_info.demo.services;

import java.security.PrivateKey;
import java.util.Base64;
import java.util.Optional;

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
        System.out.println("Teste ");
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
        user.setAlgoritmo(null);
        repository.save(user);
        
        return true;
    }

    public UserSecurity createUserSecurity(UserSecurityDto data ){
        UserSecurity createdUser = new UserSecurity();
        createdUser.setAlgoritmo(criptografia.getAlgoritmo());
        createdUser.setIdUser(data.getIdUser());
        createdUser.setKeySize(data.getKeySize());
        byte[] privateKeyBytes =  data.getPrivateKey().getEncoded(); 
        createdUser.setPrivateKey(Base64.getEncoder().encodeToString(privateKeyBytes));
        return repository.save(createdUser);
    }

    public UserSecurity updatePrivateKey(String userId, PrivateKey privateKey, String algoritmo, Integer keySize ){
        UserSecurity updatedUserSecurity = getUserSecurity(userId);
        byte[] privateKeyBytes =  privateKey.getEncoded(); 
        updatedUserSecurity.setPrivateKey(Base64.getEncoder().encodeToString(privateKeyBytes));
        updatedUserSecurity.setAlgoritmo(algoritmo);
        updatedUserSecurity.setKeySize(keySize);
        return repository.save(updatedUserSecurity);
    }
}

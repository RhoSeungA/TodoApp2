package com.example.demo.service;

import com.example.demo.model.UserEntity;
import com.example.demo.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity create(final UserEntity userEntity){
        if(userEntity==null || userEntity.getEmail() == null){
            throw new RuntimeException("Invalid arguments");
        }

        final String email=userEntity.getEmail();
        if(userRepository.existsByEmail(email)){ //중복 불가!
            log.warn("Email already exists {}",email);
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(userEntity);
    }

    public UserEntity getByCredentials(final String email, final String password , final PasswordEncoder encoder){  // 인증정보로 userEntity 검색.
        final UserEntity originalUser=userRepository.findByEmail(email);
        if(originalUser != null && encoder.matches(password,originalUser.getPassword())){
            return originalUser;
        }
        return null;

       // return userRepository.findByEmailAndPassword(email,password);
    }

}

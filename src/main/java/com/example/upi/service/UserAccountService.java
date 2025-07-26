package com.example.upi.service;

import com.example.upi.dto.UserRegistrationRequestDto;
import com.example.upi.entities.UserAccount;
import com.example.upi.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccount registerUser(UserRegistrationRequestDto request) {
        if(userAccountRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        if(userAccountRepository.findByMobile(request.getEmail()).isPresent()) {
            throw new RuntimeException("Mobile Number already registered");
        }
        UserAccount user = new UserAccount();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUpiId(request.getMobile()+"@upi");
        user.setMobile(request.getMobile());
        return userAccountRepository.save(user);
    }

}

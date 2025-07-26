package com.example.upi.service;

import com.example.upi.dto.LoginRequestDto;
import com.example.upi.dto.UserRegistrationRequestDto;
import com.example.upi.entities.UserAccount;
import com.example.upi.repository.UserAccountRepository;
import com.example.upi.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserAccountRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtUtil;

    public void register(UserRegistrationRequestDto req) {
        UserAccount user = new UserAccount();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setMobile(req.getMobile());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepo.save(user);
    }

    public String login(LoginRequestDto req) {
        UserAccount user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user);
    }
}


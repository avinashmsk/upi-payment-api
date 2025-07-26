package com.example.upi.controller;

import com.example.upi.dto.AuthResponseDto;
import com.example.upi.dto.LoginRequestDto;
import com.example.upi.dto.UserRegistrationRequestDto;
import com.example.upi.entities.UserAccount;
import com.example.upi.service.AuthService;
import com.example.upi.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    private final UserAccountService userAccountService;

    public AuthController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(@RequestBody UserRegistrationRequestDto request) {
        UserAccount registered = userAccountService.registerUser(request);
        return ResponseEntity.ok(registered);
    }

    @PostMapping(value = "/login",produces = "application/json")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        String token = authService.login(request);
        return ResponseEntity.ok(new AuthResponseDto(token));
    }
}


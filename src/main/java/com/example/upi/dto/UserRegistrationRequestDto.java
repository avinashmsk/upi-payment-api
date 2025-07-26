package com.example.upi.dto;

import lombok.Data;

@Data
public class UserRegistrationRequestDto {
    private String name;
    private String mobile;
    private String email;
    private String password;
}

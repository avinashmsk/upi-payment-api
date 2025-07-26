package com.example.upi.dto;

import lombok.Data;

@Data
public class AuthResponseDto {

    public AuthResponseDto(String token) {
        this.token = token;
    }

    private String token;

}

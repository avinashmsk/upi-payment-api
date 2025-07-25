package com.example.upi.dto;

import lombok.Data;

@Data
public class UserRegistrationRequestDto {
    private String name;
    private String upiId;
    private String mobile;
    private Double initialBalance;
}

package com.example.upi.dto;

import lombok.Data;

@Data
public class TransferRequestDto {
    private String toUpi;
    private Double amount;
}

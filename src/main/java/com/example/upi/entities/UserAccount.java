package com.example.upi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String upiId;

    private Double balance;

    @Column(unique = true)
    private String mobile;

    private LocalDateTime createdAt = LocalDateTime.now();
}

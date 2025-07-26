package com.example.upi.controller;

import com.example.upi.dto.TransferRequestDto;
import com.example.upi.dto.UserRegistrationRequestDto;
import com.example.upi.entities.UserAccount;
import com.example.upi.service.UpiService;
import com.example.upi.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upi")
public class UpiController {

    @Autowired
    private UpiService service;

    @GetMapping("/balance")
    public double checkBalance(@AuthenticationPrincipal UserDetails userDetails) {
        return service.getBalance(userDetails.getUsername());
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequestDto transferRequest,@AuthenticationPrincipal UserDetails userDetails) {
        return service.transferMoney(userDetails.getUsername(), transferRequest.getToUpi(), transferRequest.getAmount());
    }

}


package com.example.upi.controller;

import com.example.upi.dto.DepositMoneyDto;
import com.example.upi.entities.UserAccount;
import com.example.upi.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private UserAccountRepository userRepository;

    @PostMapping("/deposit")
    public ResponseEntity<String> depositMoney(@RequestBody DepositMoneyDto request,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();

        UserAccount user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
         if(user.getBalance()!=null) {
             user.setBalance(user.getBalance() + request.getAmount());
         }else{
             user.setBalance(request.getAmount());
         }
        userRepository.save(user);

        return ResponseEntity.ok("Deposit successful. New Balance: ₹" + user.getBalance());
    }
}


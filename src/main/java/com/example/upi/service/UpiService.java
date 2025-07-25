package com.example.upi.service;

import com.example.upi.entities.TransactionLog;
import com.example.upi.entities.UserAccount;
import com.example.upi.repository.TransactionLogRepository;
import com.example.upi.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpiService {

    @Autowired
    private UserAccountRepository userRepo;

    @Autowired
    private TransactionLogRepository txnRepo;

    public String transferMoney(String fromUpi, String toUpi, double amount) {
        UserAccount sender = userRepo.findByUpiId(fromUpi)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        UserAccount receiver = userRepo.findByUpiId(toUpi)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        String status;
        if (sender.getBalance() >= amount) {
            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);

            userRepo.save(sender);
            userRepo.save(receiver);
            status = "SUCCESS";
        } else {
            status = "FAILED";
        }

        TransactionLog txn = new TransactionLog();
        txn.setFromUpi(fromUpi);
        txn.setToUpi(toUpi);
        txn.setAmount(amount);
        txn.setStatus(status);
        txnRepo.save(txn);

        return status.equals("SUCCESS") ? "Transfer Successful" : "Insufficient Balance";
    }

    public double getBalance(String upiId) {
        return userRepo.findByUpiId(upiId)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getBalance();
    }
}


package com.example.upi.service;

import com.example.upi.dto.UserRegistrationRequestDto;
import com.example.upi.entities.UserAccount;
import com.example.upi.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccount registerUser(UserRegistrationRequestDto request) {
        UserAccount user = new UserAccount();
        user.setName(request.getName());
        user.setUpiId(request.getUpiId());
        user.setMobile(request.getMobile());
        user.setBalance(request.getInitialBalance());
        return userAccountRepository.save(user);
    }

}

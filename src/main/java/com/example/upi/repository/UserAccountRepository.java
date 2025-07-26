package com.example.upi.repository;

import com.example.upi.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUpiId(String upiId);

    Optional<UserAccount> findByEmail(String email);

    Optional<UserAccount> findByMobile(String mobile);


}


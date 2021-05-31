package com.example.demo.repository;

import com.example.demo.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    @Rollback(value = false)
    void createAccount(){
        Account account = Account.builder()
                .email("test@gmail.com")
                .password("abcde")
                .firstName("Yeo")
                .lastName("Jeong")
                .build();

        Account savedAccount = accountRepository.save(account);
        System.out.println(savedAccount.getId());
        System.out.println(savedAccount.getCreatedAt());
        System.out.println(savedAccount.getUpdatedAt());
    }
}
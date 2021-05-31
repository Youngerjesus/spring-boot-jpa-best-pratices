package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void createAccount(){
        Account account = Account.builder()
                .email("test@gmail.com")
                .password("abcde")
                .firstName("Yeo")
                .lastName("jeongmin")
                .build();

        System.out.println(account.getId());
        System.out.println(account.getEmail());
        System.out.println(account.getCreatedAt());
        System.out.println(account.getUpdatedAt());
    }
}
package com.example.demo.account.repository;

import com.example.demo.account.domain.Account;
import com.example.demo.account.domain.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp(){
        Account account = Account.builder()
                            .email(Email.of("test@gmail.com"))
                            .firstName("test")
                            .lastName("aaa")
                            .password("aaaab")
                            .build();

        accountRepository.save(account);
    }

    @Test
    void findById(){
        // given
        Optional<Account> optionalAccount = accountRepository.findById(1L);
        // when
        Account account = optionalAccount.get();
        // then
        assertEquals(1L, account.getId());
    }

}
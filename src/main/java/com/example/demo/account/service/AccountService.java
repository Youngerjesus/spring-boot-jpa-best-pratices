package com.example.demo.account.service;

import com.example.demo.account.domain.Account;
import com.example.demo.account.dto.AccountDto;
import com.example.demo.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account createAccount(AccountDto.SignUpRequest dto) {
        Account account = dto.toEntity();
        accountRepository.save(account);
        return account;
    }
}

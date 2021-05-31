package com.example.demo.account;

import com.example.demo.account.domain.Account;
import com.example.demo.account.dto.AccountDto;
import com.example.demo.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    AccountRepository accountRepository;

    public Account createAccount(AccountDto.SignUpRequest dto) {
        Account account = dto.toEntity();
        accountRepository.save(account);
        return account;
    }
}

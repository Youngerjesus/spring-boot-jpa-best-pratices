package com.example.demo.account;

import com.example.demo.account.dto.AccountDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;

    @PostMapping("/signup")
    public AccountDto.Response signUp(@RequestBody @Valid AccountDto.SignUpRequest dto){
        return new AccountDto.Response(accountService.createAccount(dto));
    }
}


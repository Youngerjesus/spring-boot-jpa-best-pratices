package com.example.demo.account.controller;

import com.example.demo.account.service.AccountService;
import com.example.demo.account.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/signup")
    public AccountDto.Response signUp(@RequestBody @Valid AccountDto.SignUpRequest dto){
        return new AccountDto.Response(accountService.createAccount(dto));
    }
}


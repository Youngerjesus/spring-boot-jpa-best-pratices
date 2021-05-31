package com.example.demo.account.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class AccountDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpRequest {

        @Email
        private String email;

        @NotEmpty
        private String firstName;

        @NotEmpty
        private String lastName;
    }
}

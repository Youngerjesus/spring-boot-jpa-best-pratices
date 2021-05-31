package com.example.demo.account.dto;

import com.example.demo.account.domain.Account;
import lombok.AccessLevel;
import lombok.Builder;
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

        @NotEmpty
        private String password;

        @Builder
        public SignUpRequest(@Email String email, @NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty String password) {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
        }

        public Account toEntity(){
            return Account.builder()
                    .email(com.example.demo.account.domain.Email.of(email))
                    .firstName(firstName)
                    .lastName(lastName)
                    .password(password)
                    .build();
        }
    }

    @Getter
    public static class Response {
        private String emai;
        private String firstName;
        private String lastName;

        public Response(Account account) {
            this.emai = account.getEmail().getValue();
            this.firstName = account.getFirstName();
            this.lastName = account.getLastName();
        }
    }
}

package com.example.demo.account.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"host","emailId"})
public class Email {

    @javax.validation.constraints.Email
    @Column(nullable = false, unique = true)
    private String value;

    @Builder
    public Email(String value){
        this.value = value;
    }

    public static Email of(String email){
        return new Email(email);
    }

    public String getHost(){
        int idx = value.indexOf("@");
        return value.substring(idx);
    }

    public String getEmailId(){
        int idx = value.indexOf("@");
        return value.substring(0,idx);
    }
}

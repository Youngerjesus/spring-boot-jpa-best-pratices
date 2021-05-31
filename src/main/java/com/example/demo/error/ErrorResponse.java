package com.example.demo.error;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private String message;

    @Builder
    public ErrorResponse(String message) {
        this.message = message;
    }
}

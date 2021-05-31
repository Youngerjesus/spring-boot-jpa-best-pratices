package com.example.demo.account;

import com.example.demo.account.controller.AccountController;
import com.example.demo.account.dto.AccountDto;
import com.example.demo.annotation.MockMvcTest;
import com.example.demo.error.ErrorCode;
import com.example.demo.error.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockMvcTest
class AccountControllerTest {

    @Autowired
    AccountController accountController;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createAccountSuccess() throws Exception {
        // given
        AccountDto.SignUpRequest dto = AccountDto.SignUpRequest.builder()
                                            .email("test@gamil.com")
                                            .firstName("test")
                                            .lastName("lll")
                                            .password("abcde")
                                            .build();

        AccountDto.Response response = new AccountDto.Response(dto.toEntity());
        String jsonResponse = objectMapper.writeValueAsString(response);
        //when - then
        mockMvc.perform(post("/account/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                        .andExpect(status().isOk())
                        .andExpect(content().json(jsonResponse))
                        .andDo(print());

    }

    @Test
    void createAccountFailure() throws Exception {
        //given
        AccountDto.SignUpRequest dto = AccountDto.SignUpRequest.builder()
                .email("test@gamil.com")
                .firstName("")
                .lastName("lll")
                .password("abcde")
                .build();
        //when - then
        mockMvc.perform(post("/account/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message", is(ErrorCode.INPUT_VALUE_INVALID.getMessage())))
                        .andDo(print());
    }
}
package com.example.demo.account;

import com.example.demo.account.dto.AccountDto;
import com.example.demo.annotation.MockMvcTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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
    void createAccount() throws Exception {
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
}
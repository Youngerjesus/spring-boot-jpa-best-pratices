package com.example.demo.account.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void jacksonMapping() throws JsonProcessingException, JSONException {
        //given
        ObjectMapper mapper = new ObjectMapper();
        Email email = Email.of("test@gmail.com");
        //when
        String jsonEmail = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(email);
        //then
        JSONAssert.assertEquals("{value:test@gmail.com}",jsonEmail,  true);
    }
}
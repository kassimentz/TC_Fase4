package com.fiap.tech_challenge_web_streaming.exception;

import com.fiap.tech_challenge_web_streaming.infrastructure.config.exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorMessageTest {

    @Test
     void testGetSetMessage() {
        ErrorMessage errorMessage = new ErrorMessage("Inicial Message");
        String expectedMessage = "Test Message";
        errorMessage.setMessage(expectedMessage);
        assertEquals(expectedMessage, errorMessage.getMessage());
    }

    @Test
    void testConstructor() {
        String expectedMessage = "Test Message";
        ErrorMessage errorMessage = new ErrorMessage(expectedMessage);
        assertEquals(expectedMessage, errorMessage.getMessage());
    }
}

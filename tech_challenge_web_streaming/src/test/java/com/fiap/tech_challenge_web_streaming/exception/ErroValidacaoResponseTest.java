package com.fiap.tech_challenge_web_streaming.exception;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ErroValidacaoResponseTest {

    @Test
    void testGetSetStatus() {
        ErroValidacaoResponse erroValidacaoResponse = new ErroValidacaoResponse();
        int expectedStatus = 400;
        erroValidacaoResponse.setStatus(expectedStatus);
        assertEquals(expectedStatus, erroValidacaoResponse.getStatus());
    }

    @Test
    void testGetSetMensagem() {
        ErroValidacaoResponse erroValidacaoResponse = new ErroValidacaoResponse();
        String expectedMensagem = "Test Message";
        erroValidacaoResponse.setMensagem(expectedMensagem);
        assertEquals(expectedMensagem, erroValidacaoResponse.getMensagem());
    }

    @Test
    void testGetSetErros() {
        ErroValidacaoResponse erroValidacaoResponse = new ErroValidacaoResponse();
        List<String> expectedErros = Arrays.asList("Error 1", "Error 2");
        erroValidacaoResponse.setErros(expectedErros);
        assertEquals(expectedErros, erroValidacaoResponse.getErros());
    }
}

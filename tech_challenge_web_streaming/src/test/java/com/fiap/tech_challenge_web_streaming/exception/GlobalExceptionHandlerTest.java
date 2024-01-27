package com.fiap.tech_challenge_web_streaming.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class GlobalExceptionHandlerTest {

    @Test
    void testHandleValidationException() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");
        List<ObjectError> errors = new ArrayList<>();
        errors.add(fieldError);
        when(bindingResult.getAllErrors()).thenReturn(errors);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<ErroValidacaoResponse> response = handler.handleValidationException(ex);

        assertEquals(400, response.getBody().getStatus());
        assertEquals("Erro de validação", response.getBody().getMensagem());
        assertEquals("Campo 'field': defaultMessage", response.getBody().getErros().get(0));
    }

    @Test
    void testHandleHttpMessageConversionException() {
        HttpMessageConversionException ex = mock(HttpMessageConversionException.class);

        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<ErrorMessage> response = handler.handleHttpMessageConversionException(ex);

        assertEquals("Ocorreu um erro na desserialização do JSON. Verifique os tipos dos atributos.", response.getBody().getMessage());
    }

    @Test
    void testRuntimeException() {
        RuntimeException ex = mock(RuntimeException.class);
        when(ex.getMessage()).thenReturn("Test RuntimeException");

        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<ErrorMessage> response = handler.runtimeException(ex);

        assertEquals("Test RuntimeException", response.getBody().getMessage());
    }
}

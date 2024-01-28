package com.fiap.tech_challenge_web_streaming.infrastructure.config.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErroValidacaoResponse> handleValidationException(WebExchangeBindException ex) {
        ErroValidacaoResponse response = new ErroValidacaoResponse();
        response.setStatus(400);
        response.setMensagem("Erro de validação");

        List<String> erros = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                String mensagemErro = "Campo '" + fieldError.getField() + "': " + fieldError.getDefaultMessage();
                erros.add(mensagemErro);
            } else {
                erros.add(error.getDefaultMessage());
            }
        }

        response.setErros(erros);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<ErrorMessage> handleHttpMessageConversionException(HttpMessageConversionException ex) {
        String errorMessage = "Ocorreu um erro na desserialização do JSON. Verifique os tipos dos atributos.";
        ErrorMessage error = new ErrorMessage(errorMessage);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> runtimeException(RuntimeException ex) {
        ex.printStackTrace();
        String errorMessage = ex.getMessage();
        ErrorMessage error = new ErrorMessage(errorMessage);
        return ResponseEntity.badRequest().body(error);
    }

//    @ExceptionHandler({MissingServletRequestParameterException.class})
//    public ResponseEntity<ErroValidacaoResponse> handleValidationParameterException(MissingServletRequestParameterException ex) {
//        ErroValidacaoResponse response = new ErroValidacaoResponse();
//        response.setStatus(400);
//        response.setMensagem("Erro de validação");
//
//        List<String> erros = new ArrayList<>();
//
//        String mensagemErro = "Campo '" + ex.getParameterName() + "': " + ex.getMessage();
//        erros.add(mensagemErro);
//        response.setErros(erros);
//
//        return ResponseEntity.badRequest().body(response);
//    }
}

package com.fiap.tech_challenge_web_streaming.domain.usuario.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

 class UsuarioNaoEncontradoExceptionTest {

    @Test
     void testExceptionMessage() {
        UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException();
        assertEquals("Usuario não encontrado!", exception.getMessage());
    }
}

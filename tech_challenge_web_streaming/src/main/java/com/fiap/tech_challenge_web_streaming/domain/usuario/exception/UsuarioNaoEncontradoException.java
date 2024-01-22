package com.fiap.tech_challenge_web_streaming.domain.usuario.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException() {
        super ("Usuario não encontrado!");
    }


}

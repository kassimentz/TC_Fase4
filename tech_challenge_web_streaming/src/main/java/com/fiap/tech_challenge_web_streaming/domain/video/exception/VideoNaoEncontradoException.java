package com.fiap.tech_challenge_web_streaming.domain.video.exception;

public class VideoNaoEncontradoException extends RuntimeException{

    public VideoNaoEncontradoException() {
        super ("Video não encontrado!");
    }

}

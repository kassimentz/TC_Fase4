package com.fiap.tech_challenge_web_streaming.domain.video.exception;

public class VideoNaoSalvoException extends RuntimeException{
     public VideoNaoSalvoException () {
         super("Vídeo não pode ser salvo");
     }

    public VideoNaoSalvoException(Throwable throwable) {
    }
}

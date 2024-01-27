package com.fiap.tech_challenge_web_streaming.domain.video.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

 class VideoNaoEncontradoExceptionTest {

    @Test
     void testExceptionMessage() {
        VideoNaoEncontradoException exception = new VideoNaoEncontradoException();
        assertEquals("Video não encontrado!", exception.getMessage());
    }
}

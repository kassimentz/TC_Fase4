package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fiap.tech_challenge_web_streaming.usecase.video.DeletarVideoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class DeletarVideoControllerTest {

    @Mock
    private DeletarVideoUseCase deletarVideoUseCase;

    private DeletarVideoController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new DeletarVideoController(deletarVideoUseCase);
    }

    @Test
    void testDeletarVideo() {
        when(deletarVideoUseCase.execute(anyString())).thenReturn(Mono.empty());

        Mono<ResponseEntity<Void>> result = controller.deletarVideo("testId");

        StepVerifier.create(result)
                .expectComplete();
    }
}

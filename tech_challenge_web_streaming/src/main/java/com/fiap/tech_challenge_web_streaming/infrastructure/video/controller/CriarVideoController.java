package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoRequestData;
import com.fiap.tech_challenge_web_streaming.usecase.video.CriarVideoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
@Tag(name = "Vídeo", description = "Vídeo API")
@RestController
public class CriarVideoController {

    private final CriarVideoUseCase criarVideoUseCase;


    public CriarVideoController(CriarVideoUseCase criarVideoUseCase) {
        this.criarVideoUseCase = criarVideoUseCase;
    }


    @PostMapping(value = "/videos", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Criar Vídeo")
    public Mono<ResponseEntity<VideoPublicData>> createVideo(@Valid @RequestPart VideoRequestData videoMetadata, @RequestPart FilePart videoFile) throws JsonProcessingException {


        /* Caso queiramos receber uma string e converter aqui, descomentar essas linhas
     //   ObjectMapper mapper = new ObjectMapper();
        // mapper.registerModule(new JavaTimeModule());
        //VideoRequestData videoData = mapper.readValue(videoDataJson, VideoRequestData.class);
        */


        return this.criarVideoUseCase.execute(videoMetadata, videoFile)
                .map(v -> new ResponseEntity<>(new VideoPublicData(v), HttpStatus.CREATED)
                );
    }
}

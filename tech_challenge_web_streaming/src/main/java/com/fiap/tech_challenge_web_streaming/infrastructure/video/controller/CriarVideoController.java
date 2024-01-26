package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoRequestData;
import com.fiap.tech_challenge_web_streaming.usecase.video.CriarVideoUseCase;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoRequestData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
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
    public Mono<ResponseEntity<IVideoPublicData>> createVideo(@RequestPart VideoRequestData videoMetadata, @RequestPart FilePart videoFile) throws JsonProcessingException {


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
package com.fiap.tech_challenge_web_streaming.infrastructure.video.dto;

import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoRequestData;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record VideoRequestData(
        // url e id não enviados pois serão gerados
        @NotBlank(message = "Titulo do video não pode ser vazio")
        String titulo,
        @NotBlank(message = "Descricao do video não pode ser vazio")
        String descricao,

        @NotBlank(message = "Categoria do video não pode ser vazia")
        String categoria

     ) implements IVideoRequestData {
}

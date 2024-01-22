package com.fiap.tech_challenge_web_streaming.infrastructure.video.dto;

import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoRequestData;

import java.time.LocalDate;

public record VideoRequestData(
        // url e id não enviados pois serão gerados
        //TODO Validações entram aqui
        String titulo,
        String descricao,
        String categoria,
        LocalDate dataPublicacao

     ) implements IVideoRequestData {
}

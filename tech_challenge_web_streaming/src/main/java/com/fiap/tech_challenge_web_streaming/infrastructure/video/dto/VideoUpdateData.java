package com.fiap.tech_challenge_web_streaming.infrastructure.video.dto;

import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoUpdateData;

import java.time.LocalDate;

public record VideoUpdateData(

        //TODO validações entram aqui
        String titulo,
        String descricao,
        String categoria,
        LocalDate dataPublicacao

) implements IVideoUpdateData{}
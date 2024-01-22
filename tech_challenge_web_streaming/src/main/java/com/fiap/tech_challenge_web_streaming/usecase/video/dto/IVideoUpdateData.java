package com.fiap.tech_challenge_web_streaming.usecase.video.dto;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;

import java.time.LocalDate;

public interface IVideoUpdateData {

    String titulo();
    String descricao();
    LocalDate dataPublicacao();

    String categoria();

}

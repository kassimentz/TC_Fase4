package com.fiap.tech_challenge_web_streaming.usecase.video.dto;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;

import java.time.LocalDate;

public interface IVideoUpdateData {





    default String titulo() {
        return "";
    }

    default String descricao() {
        return "";
    }

    default LocalDate dataPublicacao() {
        return LocalDate.MIN;
    }

    default String categoria() {
        return "";
    }

}

package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto;

import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioAddFavoritoRequestData;

public record UsuarioAddFavoritoRequestData(
        String videoId
) implements IUsuarioAddFavoritoRequestData {
}

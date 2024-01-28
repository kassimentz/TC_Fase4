package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto;

import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioUpdateData;

public record UsuarioUpdateData(

        String nome,
        String email

) implements IUsuarioUpdateData {
}

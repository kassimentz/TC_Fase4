package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto;

import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioRequestData;

public record UsuarioRequestData(

        //Incluir Spring Validation aqui

        String nome,
        String email



) implements IUsuarioRequestData {

}

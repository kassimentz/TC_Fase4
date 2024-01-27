package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioRequestData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UsuarioRequestData(

        //Incluir Spring Validation aqui

        @NotBlank(message = "Nome não pode ser vazio")
        String nome,

        @Email(message = "Email inválido")
        String email



) implements IUsuarioRequestData {

}

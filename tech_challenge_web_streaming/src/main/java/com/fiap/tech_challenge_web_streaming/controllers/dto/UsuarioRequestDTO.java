package com.fiap.tech_challenge_web_streaming.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioRequestDTO {

    @JsonProperty
    @Email(message = "Digite um nome válido")
    @NotBlank(message = "O nome não pode ser nulo")
    private String nome;

    @JsonProperty
    @Email(message = "Digite um e-mail válido")
    @NotBlank(message = "O e-mail não pode ser nulo")
    private String email;

    public UsuarioRequestDTO(){
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}

package com.fiap.tech_challenge_web_streaming.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.tech_challenge_web_streaming.entities.Video;

import java.util.List;

public class UsuarioResponseDTO {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String nome;
    @JsonProperty
    private String email;
    @JsonProperty
    private List<Video> favoritos;
    @JsonProperty
    private List<Video> recomendados;

    public UsuarioResponseDTO(){
    }

    public UsuarioResponseDTO(Long id, String nome, String email, List<Video> favoritos, List<Video> recomendados){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.favoritos = favoritos;
        this.recomendados = recomendados;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
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

    public List<Video> getFavoritos(){
        return favoritos;
    }

    public void setFavoritos(List<Video> favoritos){
        this.favoritos = favoritos;
    }

    public List<Video> getRecomendados(){
        return recomendados;
    }

    public void setRecomendados(List<Video> recomendados){
        this.recomendados = recomendados;
    }
}

package com.fiap.tech_challenge_web_streaming.entities;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioRequestDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    private String nome;
    private String email;
    private List<String> videosFavoritados;

    private List<String> videosRecomendados;

    public Usuario(){
        this.videosFavoritados = new ArrayList<>();
        this.videosRecomendados = new ArrayList<>();
    }

    public Usuario(String id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Usuario(String id, String nome, String email, List<String> favoritos, List<String> videosRecomendados){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.videosFavoritados = favoritos;
        this.videosRecomendados = videosRecomendados;
    }

    public Usuario(UsuarioRequestDTO request){
        this.nome = request.getNome();
        this.email = request.getEmail();
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
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

    public List<String> getVideosFavoritados(){
        return videosFavoritados;
    }

    public void setVideosFavoritados(List<String> videosFavoritados){
        this.videosFavoritados = videosFavoritados;
    }

    public void favoritarVideo(String idVideo) {
        this.videosFavoritados.add(idVideo);
    }

    public List<String> getVideosRecomendados(){
        return videosRecomendados;
    }

    public void setVideosRecomendados(List<String> videosRecomendados){
        this.videosRecomendados = videosRecomendados;
    }

    public void addVideoRecomendado(String idVideo) {
        this.videosRecomendados.add(idVideo);
    }
}

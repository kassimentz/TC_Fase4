package com.fiap.tech_challenge_web_streaming.entities;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioRequestDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    private String nome;
    private String email;
    private List<Video> favoritos;
    private List<Video> recomendados;

    public Usuario(){
    }

    public Usuario(String id, String nome, String email, List<Video> favoritos, List<Video> recomendados){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.favoritos = favoritos;
        this.recomendados = recomendados;
    }

    public Usuario(String id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Usuario(UsuarioRequestDTO requestDTO) {
        this.nome = requestDTO.getNome();
        this.email = requestDTO.getEmail();
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

    public void addFavorito(Video video) {
        this.favoritos.add(video);
    }

//    public List<Video> getFavoritos(){
//        return Collections.unmodifiableList(favoritos);
//    }
}

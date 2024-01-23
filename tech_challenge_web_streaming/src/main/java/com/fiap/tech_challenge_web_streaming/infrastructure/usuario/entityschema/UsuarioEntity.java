package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.entityschema;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "usuarios")
public class UsuarioEntity {

    @Id
    private String id;
    private String nome;
    private String email;
    private List<Video> favoritos;
    private List<Video> recomendados;

    public UsuarioEntity(){
        favoritos = new ArrayList<>();
        recomendados = new ArrayList<>();
    }

    public UsuarioEntity(String id, String nome, String email, List<Video> favoritos, List<Video> recomendados){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.favoritos = favoritos;
        this.recomendados = recomendados;
    }

    public UsuarioEntity(String id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public UsuarioEntity(Usuario usuario) {
        this.nome= usuario.getNome();
        this.email = usuario.getEmail();
        this.favoritos = usuario.getFavoritos();
        this.recomendados = usuario.getRecomendados();


    }

    public Usuario toUsuario() {
        return new Usuario(this.id, this.nome, this.email, this.getFavoritos(), this.recomendados);
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

}

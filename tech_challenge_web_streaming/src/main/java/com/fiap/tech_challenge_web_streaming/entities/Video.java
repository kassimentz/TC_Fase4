package com.fiap.tech_challenge_web_streaming.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "videos")
public class Video {

    @Id
    private String id;
    private String titulo;
    private String descricao;
    private String url;
    private LocalDate dataPublicacao;
    private Categoria categoria;

    private List<String> favoritadoPorUsuarios; // new field

    public Video(){
        favoritadoPorUsuarios = new ArrayList<>();
    }

    public Video(String id, String titulo, String descricao, String url, LocalDate dataPublicacao, Categoria categoria, List<String> favoritadoPorUsuarios){
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.favoritadoPorUsuarios = favoritadoPorUsuarios;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public LocalDate getDataPublicacao(){
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao){
        this.dataPublicacao = dataPublicacao;
    }

    public Categoria getCategoria(){
        return categoria;
    }

    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
    }

    public List<String> getFavoritadoPorUsuarios(){
        return favoritadoPorUsuarios;
    }

    public void setFavoritadoPorUsuarios(List<String> favoritadoPorUsuarios){
        this.favoritadoPorUsuarios = favoritadoPorUsuarios;
    }

    public void addFavoritadoPorUsuarios(String usuarioId) {
        this.favoritadoPorUsuarios.add(usuarioId);
    }
}

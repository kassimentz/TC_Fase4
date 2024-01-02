package com.fiap.tech_challenge_web_streaming.entities;

import java.time.LocalDate;
import java.util.List;

public class Video {
    private String titulo;
    private String descricao;
    private String url;
    private LocalDate dataPublicacao;
    private List<Categoria> categorias;

    public Video(){
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

    public List<Categoria> getCategorias(){
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias){
        this.categorias = categorias;
    }
}

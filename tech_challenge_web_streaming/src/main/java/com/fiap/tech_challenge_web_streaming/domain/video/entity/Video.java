package com.fiap.tech_challenge_web_streaming.domain.video.entity;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;

import java.time.LocalDate;

public class Video {

    private String id;
    private String titulo;
    private String descricao;
    private String url;
    private LocalDate dataPublicacao;
    private Categoria categoria;


    public Video() {}


    public Video(String titulo, String descricao, LocalDate dataPublicacao, Categoria categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
    }

    public Video(String id, String titulo, String descricao, String url, LocalDate dataPublicacao, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

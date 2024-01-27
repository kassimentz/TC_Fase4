package com.fiap.tech_challenge_web_streaming.domain.video.entity;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Video {

    private String id;
    private String titulo;
    private String descricao;
    private String url;
    private LocalDate dataPublicacao;
    private Categoria categoria;
    private Long qtVisualizacao;
    private List<String> usuariosQueFavoritaram = new ArrayList<>();

    public Video() {}


    public Video(String titulo, String descricao, LocalDate dataPublicacao, Categoria categoria, String url, Long qtVisualizacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.url = url;
        this.qtVisualizacao = qtVisualizacao;
    }

    public Video(String id, String titulo, String descricao, String url, LocalDate dataPublicacao, Categoria categoria, Long qtVisualizacao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.qtVisualizacao = qtVisualizacao;
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

    public List<String> getUsuariosQueFavoritaram(){
        return usuariosQueFavoritaram;
    }

    public void setUsuariosQueFavoritaram(List<String> usuariosQueFavoritaram){
        this.usuariosQueFavoritaram = usuariosQueFavoritaram;
    }

    public Long getQtVisualizacao() {
        return qtVisualizacao;
    }

    public void setQtVisualizacao(Long qtVisualizacao) {
        this.qtVisualizacao = qtVisualizacao;
    }

    public void favoritarVideoPorUsuario(String usuarioId) {
        this.usuariosQueFavoritaram.add(usuarioId);
    }
}

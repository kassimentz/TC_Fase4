package com.fiap.tech_challenge_web_streaming.infrastructure.video.entityschema;


import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;

@Document(collection = "videos")
public class VideoEntity {

    @Id
    private String id;
    private String titulo;
    private String descricao;
    private String url;
    private Long qtVisualizacao;
    private LocalDate dataPublicacao;
    private Categoria categoria;


    public VideoEntity(){};

    public VideoEntity(Video video){
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.dataPublicacao = video.getDataPublicacao();
        this.categoria = video.getCategoria();
 this.qtVisualizacao = video.getQtVisualizacao();

    }

    public Video toVideo() {


        return new Video(
                this.id, this.titulo, this.descricao, this.url, this.dataPublicacao, this.categoria, this.qtVisualizacao);
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

    public Long getQtVisualizacao() {
        return qtVisualizacao;
    }

    public void setQtVisualizacao(Long qtVisualizacao) {
        this.qtVisualizacao = qtVisualizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoEntity that = (VideoEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

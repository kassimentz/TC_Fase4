package com.fiap.tech_challenge_web_streaming.domain.video.factories;

import java.time.LocalDate;

public class CriteriosBuscaVideo {

    private String titulo;
    private LocalDate dataPublicacao;
    private String categoria;

    private CriteriosBuscaVideo(Builder builder) {
        this.titulo = builder.titulo;
        this.dataPublicacao = builder.dataPublicacao;
        this.categoria = builder.categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {


        private String titulo;
        private LocalDate dataPublicacao;
        private String categoria;

        private Builder(){};


        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder dataPublicacao(LocalDate dataPublicacao){
            this.dataPublicacao = dataPublicacao;
            return this;
        }

        public Builder categoria(String categoria) {
            this.categoria = categoria;
            return this;
        }

        public CriteriosBuscaVideo build() {
            return new CriteriosBuscaVideo(this);
        }

    }
}

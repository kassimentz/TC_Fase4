package com.fiap.tech_challenge_web_streaming.domain.usuario.entity;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;

import java.util.ArrayList;
import java.util.List;

public class Usuario {


        private String id;
        private String nome;
        private String email;
        private List<Video> favoritos;
        private List<Video> recomendados;

        public Usuario(){
            this.favoritos = new ArrayList<>();
            this.recomendados = new ArrayList<>();
        }

        public Usuario(String id, String nome, String email, List<Video> favoritos, List<Video> recomendados){
            this.id = id;
            this.nome = nome;
            this.email = email;
            this.favoritos = favoritos;
            this.recomendados = recomendados;
        }

    public Usuario( String nome, String email, List<Video> favoritos, List<Video> recomendados){
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

    public Usuario(String nome, String email) {
            this.nome = nome;
            this.email = email;
            this.favoritos = new ArrayList<>();
            this.recomendados = new ArrayList<>();
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

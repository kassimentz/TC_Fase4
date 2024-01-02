package com.fiap.tech_challenge_web_streaming.entities;

public enum Categoria {
    ENGRACADO("engraçado"),
    TECNOLOGIA("tecnologia"),
    PETS("pets");

    private String categoria;
    Categoria(String descricao) {
        this.categoria = descricao;
    }

    public String getCategoria() {
        return categoria;
    }
}

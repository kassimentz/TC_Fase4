package com.fiap.tech_challenge_web_streaming.domain.video.factories;

public class PageData {

    private int page;
    private int size;
    private String direcao;
    private String ordenacao;

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getDirecao() {
        return direcao;
    }

    public String getOrdenacao() {
        return ordenacao;
    }

    public PageData(PageBuilder builder) {
        this.page = builder.page;
        this.size = builder.size;
        this.direcao = builder.direcao;
        this.ordenacao = builder.ordenacao;
    }

    public static PageBuilder newPageBuilder() {
        return new PageBuilder();
    }

    public static final class PageBuilder {

        private int page;
        private int size;
        private String direcao;
        private String ordenacao;


        private PageBuilder() {}

        public PageBuilder page(int page) {
            this.page = page;
            return this;
        }

        public PageBuilder size(int size) {
            this.size = size;
            return this;
        }

        public PageBuilder direcao(String direcao) {
            this.direcao = direcao;
            return this;
        }

        public PageBuilder ordenacao(String ordenacao) {
            this.ordenacao = ordenacao;
            return this;
        }

        public PageData build() {
            return new PageData(this);
        }

    }
}

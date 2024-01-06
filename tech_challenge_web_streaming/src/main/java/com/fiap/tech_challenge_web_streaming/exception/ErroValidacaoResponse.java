package com.fiap.tech_challenge_web_streaming.exception;

import java.util.List;

public class ErroValidacaoResponse {

    private int status;
    private String mensagem;
    private List<String> erros;

    public ErroValidacaoResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}

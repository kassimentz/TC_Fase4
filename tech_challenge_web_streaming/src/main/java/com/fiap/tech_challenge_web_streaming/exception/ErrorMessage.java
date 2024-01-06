package com.fiap.tech_challenge_web_streaming.exception;

public class ErrorMessage {

    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.exo.demo.response;

public class ResponseBean<T> {
    private String status;
    private String message;
    private T content;
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";

    public ResponseBean() {
    }

    public ResponseBean(String status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
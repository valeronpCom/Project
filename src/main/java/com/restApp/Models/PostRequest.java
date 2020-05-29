package com.restApp.Models;

public class PostRequest {
    Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public PostRequest(Integer number) {
        this.number = number;
    }

    public PostRequest() {
    }
}

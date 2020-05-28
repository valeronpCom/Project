package com.restApp.Models;

public class PostRequest {
    String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PostRequest(String number) {
        this.number = number;
    }

    public PostRequest() {
    }
}

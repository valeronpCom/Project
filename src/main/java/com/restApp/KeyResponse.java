package com.restApp;

public class KeyResponse {

    private String answerOfGuessing;

    public KeyResponse(){};

    public KeyResponse(String answer){
        this.answerOfGuessing = answer;
    }

    public String getAnswerOfGuessing() {
        return answerOfGuessing;
    }

    public void setAnswerOfGuessing(String answerOfGuessing) {
        this.answerOfGuessing = answerOfGuessing;
    }

}

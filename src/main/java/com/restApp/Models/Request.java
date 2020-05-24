package com.restApp.Models;

import javax.persistence.*;

@Entity
@Table(name = "lab7")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "answer")
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public int getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
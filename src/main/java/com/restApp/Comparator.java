package com.restApp;

public class Comparator {
    private Integer guessedNumber;
    private Integer number;

    public Comparator(Integer number, Integer guessedNumber) {
        this.guessedNumber = guessedNumber;
        this.number = number;
    }
    public Integer getGuessedNumber() {
        return guessedNumber;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setGuessedNumber(Integer guessedNumber) {
        this.guessedNumber = guessedNumber;
    }
}

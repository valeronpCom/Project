package com.restApp.Services;

import java.util.Random;

public class KeyService {

    private static Random random = new Random();
    private static int keyNumber = 1 + random.nextInt( 5 - 1 + 1);
    private Integer guessedNumber;

    public KeyService(Integer number) {
        this.guessedNumber = number;
    }

    public String checkNumber() {
        String answer = " ";
        if (guessedNumber < keyNumber) {
            answer = "Введённое числе меньше загаданного";
        }
        if (guessedNumber > keyNumber) {
            answer = "Введённое чилсо больше загаданного";
        }
        if (guessedNumber == keyNumber) {
            answer = "Вы угадали загаданное число";
        }
        return answer;

    }

    public Integer getGuessedNumber() {
        return guessedNumber;
    }

    public void setGuessedNumber(Integer guessedNumber){
        this.guessedNumber = guessedNumber;
    }

}

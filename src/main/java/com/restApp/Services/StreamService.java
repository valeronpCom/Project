package com.restApp.Services;

import com.restApp.Models.Comparator;

import java.util.stream.Stream;

public class StreamService {
    public Stream<String> countNumbers(Stream<Comparator> data) {
        return data.parallel().map(this::countNumbers);
    }

    public String countNumbers(Comparator comparator) {
        String answer = " ";
        if (comparator.getGuessedNumber() < comparator.getNumber()) {
            answer = "Введённое числе меньше загаданного";
        }
        if (comparator.getGuessedNumber() > comparator.getNumber()) {
            answer = "Введённое чилсо больше загаданного";
        }
        if (comparator.getGuessedNumber() == comparator.getNumber()) {
            answer = "Вы угадали загаданное число";
        }
        return answer;
    }
}


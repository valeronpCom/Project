package com.restApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class KeyService {

    private static Random random = new Random();
    private static int keyNumber = 1 + random.nextInt( 5 - 1 + 1);
    private Integer guessedNumber;

    List<Comparator> list = new ArrayList<>();
    StreamService streamService = new StreamService();

    public KeyService(Integer number) {
        this.guessedNumber = number;
    }

    public String checkNumber() {
        list.add(new Comparator(keyNumber, guessedNumber));
        Stream<String> stream =  streamService.countNumbers(list.stream());
        return stream.findFirst().orElse("");
    }

    public Integer getGuessedNumber() {
        return guessedNumber;
    }

    public void setGuessedNumber(Integer guessedNumber){
        this.guessedNumber = guessedNumber;
    }

}

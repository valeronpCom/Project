package com.restApp;

import org.springframework.stereotype.Component;

@Component
public class Counter {

    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    synchronized public void increaseCounter(){
        counter++;
    }

}

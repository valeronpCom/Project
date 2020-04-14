package com.restApp;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class KeyCache {

    Map<Integer,KeyResponse> cache;

    public KeyCache(){
        cache = new HashMap<>();
    }
    public boolean checkForExistingInCache(Integer number){
        return cache.containsKey(number);
    }

    public KeyResponse getValue(Integer number){
        return cache.get(number);
    }

    public void setValue(Integer number, KeyResponse service){
        cache.put(number,service);
    }

}